package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.google.firebase.firestore.Query;

import java.util.ArrayList;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.DiaryRecyclerViewScrollListener;
import codingadventure.community.myapp.listEventPack.FirestorePagingListener;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import jp.wasabeef.richeditor.RichEditor;


/**리스트 형태로 띄워줄 페이지(프래그먼트) */
public class DiaryList_ListType extends Fragment{

    //private DocumentSnapshot lastVisible;

    /**리스트 (파이어베이스에서 가져와 입력)*/
    ArrayList<UserDiaryWrite> diaryBox = new ArrayList<>();
    ArrayList<String> DocumentIdBox = new ArrayList<>();

    FirestorePagingListener pagingListener;

    FrameLayout get_fullsize_diary;

    EditText titleEditText;
    RichEditor contentEditor;
    Switch publicityStatus;

    ImageButton rightButton;

    public DiaryList_ListType(){

    }

    public DiaryList_ListType(ImageButton rightButton){
        this.rightButton = rightButton;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.diarylist_listtype_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.listType_userDiary_RecyclerView);
        ProgressBar progressBar = view.findViewById(R.id.listType_paging_progressBar);
        Button closeButton = view.findViewById(R.id.listType_closeDiary_button);
        ConstraintLayout fulldiaryLayout_back = view.findViewById(R.id.listType_backblur_ConstraintLayout);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false); // LinearLayout형태로 리싸이클러뷰를 지정

        recyclerView.setLayoutManager(layoutManager);   //layout manager을 적용

        setDiaryLayout(view);

        /**item클릭 리스너 연결 , 디자인탭의 경우 직접적인 파라미터를 전달하지 않고 사용할 수 있게끔 오버라이딩 메소드로 작성*/


        DiaryAdapter adapter = new DiaryAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(View view,int position) {
                Log.e("눌리긴 하네 ","흠냐링");
                rightButton.setVisibility(View.INVISIBLE);

                UserDiaryWrite diary_data = pagingListener.getListBox(position);

                fulldiaryLayout_back.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);

                //TextView textView = view.findViewById(R.id.listCycler_title_TextView);
                titleEditText.setText(diary_data.getTitle());

                //RichEditor richEditor = view.findViewById(R.id.listCycler_content_RecyclerRichEditor);
                contentEditor.setHtml(diary_data.getContent());

                //Switch choice_switch = view.findViewById(R.id.listCycler_choice_switchButton);
                publicityStatus.setChecked(diary_data.isPublicityStatus());

            }
        });  // 어댑터 객체 생성

        adapter.setItems(diaryBox);

        recyclerView.setAdapter(adapter);


        pagingListener = new FirestorePagingListener(DocumentIdBox,diaryBox,adapter,progressBar);

        Query query = DiaryListLoad.getMyDiaryQury();
        query.get().addOnCompleteListener(pagingListener);



        /**리싸이클러 뷰 리스너 적용*/
        recyclerView.addOnScrollListener(new DiaryRecyclerViewScrollListener(layoutManager){
            @Override
            public void onLoadMore(int totalItemsCount, RecyclerView view) {

                Query nextQuery = DiaryListLoad.getMyDiaryQury().startAfter(pagingListener.getDocumentSnapshot());
                nextQuery.get().addOnCompleteListener(pagingListener);



            }
        }); // 리싸이클러 뷰를 스크롤 할 경우의 이벤트 리스너


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fulldiaryLayout_back.setVisibility(View.INVISIBLE);
                closeButton.setVisibility(View.INVISIBLE);
                rightButton.setVisibility(View.VISIBLE);
            }
        });


        return view;
    }
    /**프레임 레이아웃에 xml파일 적용을 위한 메소드*/
    private void setDiaryLayout(View view){
        get_fullsize_diary = view.findViewById(R.id.listType_fullSizeDiary_FrameLayout);    // 풀 사이즈의 다이어리를 보여줄 레이아웃


        LayoutInflater fragmentInflater = LayoutInflater.from(view.getContext());   //프래그먼트의 생명주기를 가진 inflater
        View childView = fragmentInflater.inflate(R.layout.diary_full_page, null);  //해당 view(풀사이즈 다이어리 xml 파일)을 가져옴
        set_fullsizeDiary_Component(childView);


        get_fullsize_diary.addView(childView);//적용

    }
    private void set_fullsizeDiary_Component(View childView){
        titleEditText = childView.findViewById(R.id.fulldiary_Title_editText);
        contentEditor = childView.findViewById(R.id.fulldiary_content_richeditor);
        publicityStatus = childView.findViewById(R.id.fulldiary_publicityStatus_Switch);
    }


}