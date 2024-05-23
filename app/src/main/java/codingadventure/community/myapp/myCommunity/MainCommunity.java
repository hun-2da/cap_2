package codingadventure.community.myapp.myCommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.DiaryRecyclerViewScrollListener;
import codingadventure.community.myapp.listEventPack.FirestorePagingListener;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.myCommunity.CommunityTool.CommunityAdapter;
import codingadventure.community.myapp.myCommunity.CommunityTool.CommunityListLoad;
import codingadventure.community.myapp.myCommunity.CommunityTool.SpinnerSelectedListener;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.myCommunity.viewDiary.UserDiary_SlidingPage;
import codingadventure.community.myapp.myCommunity.viewDiary.DiaryPage;

public class MainCommunity extends AppCompatActivity {
    private String[] spinner_Items = new String[]{
            FirebaseDBNameClass.DIARY_CATEGORY_ALL,
            FirebaseDBNameClass.DIARY_CATEGORY_PRIDE,
            FirebaseDBNameClass.DIARY_CATEGORY_GREED,
            FirebaseDBNameClass.DIARY_CATEGORY_LUST,
            FirebaseDBNameClass.DIARY_CATEGORY_ENVY,
            FirebaseDBNameClass.DIARY_CATEGORY_GLUTTONY,
            FirebaseDBNameClass.DIARY_CATEGORY_WRATH,
            FirebaseDBNameClass.DIARY_CATEGORY_SLOTH
    };
    Spinner spinner;

    ArrayList<UserDiaryWrite> diaryBox = new ArrayList<>();
    ArrayList<String> documentIDBox = new ArrayList<>();

    FirestorePagingListener pagingListener;
    ProgressBar progressBar;

    RecyclerView recyclerView;

    CommunityAdapter communityAdapter;
    LinearLayoutManager layoutManager;

    public static FrameLayout FragmentFrameLayout;
    public static ConstraintLayout BlurConstraintLayout;
    DiaryPage fragment;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    UserDiary_SlidingPage communitySlidingPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communitylist_main_activity);
        progressBar = findViewById(R.id.community_progressBar);


        setSpinner();
        setRecyclerView();
        setDiaryPage();

    }

    private void setDiaryPage() {
        BlurConstraintLayout = findViewById(R.id.community_constraintLayout);
        FragmentFrameLayout = findViewById(R.id.community_FrameLayout);

        translateLeftAnim = AnimationUtils.loadAnimation(this,R.anim.community_diary_left);
        translateRightAnim = AnimationUtils.loadAnimation(this,R.anim.community_diary_right);


        fragment = new DiaryPage();

        communitySlidingPage = new UserDiary_SlidingPage(fragment);

        translateLeftAnim.setAnimationListener(communitySlidingPage);
        translateRightAnim.setAnimationListener(communitySlidingPage);

        fragment.setSlidingPage(translateRightAnim);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.community_FrameLayout, fragment);
        fragmentTransaction.commit();

    }


    /**리싸이클러 뷰를 지정해두기 위한 메소드*/
    private void setRecyclerView(){
        recyclerView = findViewById(R.id.community_recyclerView);
        layoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false); // LinearLayout형태로 리싸이클러뷰를 지정

        recyclerView.setLayoutManager(layoutManager);   //layout manager을 적용

        communityAdapter = setRecyclerViewOnClick(recyclerView);
        communityAdapter.setItems(diaryBox);

        pagingListener = new FirestorePagingListener(documentIDBox,diaryBox,communityAdapter,progressBar); // 작업 성공 및 실패등을 관리하는 리스너



        setRecyclerViewScroll(layoutManager);


    }
    private void setRecyclerViewScroll(LinearLayoutManager layoutManager){

        /**리싸이클러 뷰 리스너 적용*/
        recyclerView.addOnScrollListener(new DiaryRecyclerViewScrollListener(layoutManager){
            @Override
            public void onLoadMore(int totalItemsCount, RecyclerView view) {

                Query nextQuery =  CommunityListLoad.getMyCommunityQury().startAfter(pagingListener.getDocumentSnapshot());
                int category_num = spinner.getSelectedItemPosition();
                Query changeQuery = getSpinnerQuery(category_num,nextQuery);
                changeQuery.get().addOnCompleteListener(pagingListener);

            }
        }); // 리싸이클러 뷰를 스크롤 할 경우의 이벤트 리스너
    }
    private CommunityAdapter setRecyclerViewOnClick(RecyclerView recyclerView){
        CommunityAdapter adapter = new CommunityAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //UserDiaryWrite diary_data = pagingListener.getListBox(position);
                UserDiaryWrite diary_data = diaryBox.get(position);
                String documentID = documentIDBox.get(position);

                fragment.setDiary(diary_data,documentID);

                UserDiary_SlidingPage.fullCommunitySwitch = true;

                BlurConstraintLayout.setVisibility(View.VISIBLE);
                FragmentFrameLayout.setVisibility(View.VISIBLE);
                FragmentFrameLayout.startAnimation(translateLeftAnim);

            }
        });  // 어댑터 객체 생성



        recyclerView.setAdapter(adapter);

        return adapter;
    }


    /**스피너를 설정하기 위한 메소드*/
    private void setSpinner(){
        //ConstraintLayout backColor = findViewById(R.id.community_background);
        ImageView imageView = findViewById(R.id.community_category_imageView);


        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_Items);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // spinner가 어떻게 보여질껀지를 나타내고 있다.
        spinner = findViewById(R.id.community_SALIGIA_spinner);
        spinner.setAdapter(spinnerAdapter);


        spinner.setOnItemSelectedListener(new SpinnerSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                super.onItemSelected(parent, view, position, id);
                communityAdapter.clearData();

                //diaryBox.clear();

                Query query = CommunityListLoad.getMyCommunityQury();
                Query changeQuery = getSpinnerQuery(position,query);

                changeQuery
                        .get()
                        .addOnCompleteListener(pagingListener);


                imageView.setImageResource(imageRes);
            }
        });

    }
    private Query getSpinnerQuery(int category_num,Query query){

        Query changeQuery = query;
        String category = "";


        switch (category_num){
            case FirebaseDBNameClass.COMMUNITY_SPINNER_NUMBER_PRIDE:
                category = FirebaseDBNameClass.DIARY_CATEGORY_PRIDE;
                break;
            case FirebaseDBNameClass.COMMUNITY_SPINNER_NUMBER_GREED:
                category = FirebaseDBNameClass.DIARY_CATEGORY_GREED;
                break;
            case FirebaseDBNameClass.COMMUNITY_SPINNER_NUMBER_LUST:
                category = FirebaseDBNameClass.DIARY_CATEGORY_LUST;
                break;
            case FirebaseDBNameClass.COMMUNITY_SPINNER_NUMBER_ENVY:
                category = FirebaseDBNameClass.DIARY_CATEGORY_ENVY;
                break;
            case FirebaseDBNameClass.COMMUNITY_SPINNER_NUMBER_GLUTTONY:
                category = FirebaseDBNameClass.DIARY_CATEGORY_GLUTTONY;
                break;
            case FirebaseDBNameClass.COMMUNITY_SPINNER_NUMBER_WRATH:
                category = FirebaseDBNameClass.DIARY_CATEGORY_WRATH;
                break;
            case FirebaseDBNameClass.COMMUNITY_SPINNER_NUMBER_SLOTH:
                category = FirebaseDBNameClass.DIARY_CATEGORY_SLOTH;
                break;
        }

        if(!category.equals("")) {
            changeQuery = changeQuery.whereEqualTo(FirebaseDBNameClass.DIARY_Category, category);
        }

        return changeQuery;
    }
}