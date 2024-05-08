package codingadventure.community.myapp.myCommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.DiaryRecyclerViewScrollListener;
import codingadventure.community.myapp.listEventPack.FirestorePagingListener;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.myCommunity.CommunityTool.CommunityAdapter;
import codingadventure.community.myapp.myCommunity.CommunityTool.CommunityListLoad;
import codingadventure.community.myapp.myCommunity.CommunityTool.SpinnerSelectedListener;
import codingadventure.community.myapp.myDiary.Diary_db_Write;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryAdapter;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryListLoad;

public class MainCommunity extends AppCompatActivity {
    private String[] spinner_Items = new String[]{"ALL","Pride", "Greed", "Lust", "Envy", "Gluttony","Wrath","Sloth"};
    ArrayList<Diary_db_Write> diaryBox = new ArrayList<>();

    FirestorePagingListener pagingListener;
    ProgressBar progressBar;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communitylist_main_activity);
        progressBar = findViewById(R.id.community_progressBar);


        setSpinner();
        setRecyclerView();



    }
    /**리싸이클러 뷰를 지정해두기 위한 메소드*/
    private void setRecyclerView(){
        recyclerView = findViewById(R.id.community_recyclerView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false); // LinearLayout형태로 리싸이클러뷰를 지정
        recyclerView.setLayoutManager(layoutManager);   //layout manager을 적용

        CommunityAdapter adapter = setRecyclerViewOnClick(recyclerView);

        adapter.setItems(diaryBox);



        pagingListener = new FirestorePagingListener(diaryBox,adapter,progressBar);

        Query query = CommunityListLoad.getMyCommunityQury();
        query.get().addOnCompleteListener(pagingListener);

        setRecyclerViewScroll(layoutManager);



    }
    private void setRecyclerViewScroll(LinearLayoutManager layoutManager){

        /**리싸이클러 뷰 리스너 적용*/
        recyclerView.addOnScrollListener(new DiaryRecyclerViewScrollListener(layoutManager){
            @Override
            public void onLoadMore(int totalItemsCount, RecyclerView view) {

                Query nextQuery =  CommunityListLoad.getMyCommunityQury().startAfter(pagingListener.getDocumentSnapshot());
                nextQuery.get().addOnCompleteListener(pagingListener);

            }
        }); // 리싸이클러 뷰를 스크롤 할 경우의 이벤트 리스너
    }
    private CommunityAdapter setRecyclerViewOnClick(RecyclerView recyclerView){
        CommunityAdapter adapter = new CommunityAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Diary_db_Write diary_data = pagingListener.getListBox(position);

                /*fulldiaryLayout_back.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);

                //TextView textView = view.findViewById(R.id.listCycler_title_TextView);
                titleEditText.setText(diary_data.getTitle());

                //RichEditor richEditor = view.findViewById(R.id.listCycler_content_RecyclerRichEditor);
                contentEditor.setHtml(diary_data.getContent());

                //Switch choice_switch = view.findViewById(R.id.listCycler_choice_switchButton);
                publicityStatus.setChecked(diary_data.isUser_publicityStatus());*/

            }
        });  // 어댑터 객체 생성



        recyclerView.setAdapter(adapter);

        return adapter;
    }


    /**스피너를 설정하기 위한 메소드*/
    private void setSpinner(){
        //ConstraintLayout backColor = findViewById(R.id.community_background);
        ImageView imageView = findViewById(R.id.community_category_imageView);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinner_Items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // spinner가 어떻게 보여질껀지를 나타내고 있다.
        Spinner spinner = findViewById(R.id.community_SALIGIA_spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new SpinnerSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                super.onItemSelected(parent, view, position, id);
                //backColor.setBackgroundColor(Color.parseColor(super.colorString));

                imageView.setImageResource(imageRes);
            }
        });

    }
}