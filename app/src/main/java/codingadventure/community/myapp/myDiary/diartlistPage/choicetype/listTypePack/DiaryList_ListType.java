package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.Diary_db_Write;


/**리스트 형태로 띄워줄 페이지(프래그먼트) */
public class DiaryList_ListType extends Fragment{

    //private DocumentSnapshot lastVisible;

    /**리스트 (파이어베이스에서 가져와 입력)*/
    ArrayList<Diary_db_Write> diaryBox = new ArrayList<>();

    FirestorePagingListener pagingListener;
    public DiaryList_ListType(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.diarylist_listtype_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.listType_userDiary_RecyclerView);


        ProgressBar progressBar = view.findViewById(R.id.listType_paging_progressBar);


        LinearLayoutManager layoutManager =
                new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false); // LinearLayout형태로 리싸이클러뷰를 지정

        recyclerView.setLayoutManager(layoutManager);   //layout manager을 적용

        DiaryAdapter adapter = new DiaryAdapter(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });  // 어댑터 객체 생성

        adapter.setItems(diaryBox);

        recyclerView.setAdapter(adapter);



        pagingListener = new FirestorePagingListener(diaryBox,adapter,progressBar);

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


        return view;
    }
    private void setDiaryLayout(){
        if (getActivity() != null) {
            // getActivity()가 null이 아닌지 확인
            FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
            // 이제 fragmentManager를 사용하여 프래그먼트 트랜잭션 등을 관리할 수 있습니다.

            fragmentManager.beginTransaction()
                    .add(R.id.listType_fullSizeDiary_FrameLayout, new DiaryList_ListType())
                    .commit();

        }

    }


}