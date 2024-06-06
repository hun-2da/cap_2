package codingadventure.community.myapp.listEventPack;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import codingadventure.community.myapp.myCommunity.CommunityTool.CommunityAdapter;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryAdapter;

public class FirestorePagingListener implements OnCompleteListener<QuerySnapshot> {
    private final ArrayList<UserDiaryWrite> diaryBox;
    private final ArrayList<String> documentIDBox;

    private DiaryAdapter diaryadapter;
    private CommunityAdapter communityadapter;

    private ProgressBar progressBar;

    private DocumentSnapshot lastVisible;

    public FirestorePagingListener(ArrayList<String> documentIDBox,ArrayList<UserDiaryWrite> diaryBox, DiaryAdapter adapter, ProgressBar progressBar) {
        this.documentIDBox = documentIDBox;
        this.diaryBox = diaryBox;
        diaryadapter = adapter;
        this.progressBar = progressBar;
    }
    public FirestorePagingListener(ArrayList<String> documentIDBox,ArrayList<UserDiaryWrite> diaryBox, CommunityAdapter adapter, ProgressBar progressBar) {
        this.documentIDBox = documentIDBox;
        this.diaryBox = diaryBox;
        communityadapter = adapter;
        this.progressBar = progressBar;
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            progressBar.setVisibility(View.VISIBLE);
            ArrayList<UserDiaryWrite> fetchedItems = new ArrayList<>();
            ArrayList<String> CommunityDocumentId = new ArrayList<>();

            for (DocumentSnapshot document : task.getResult()) {
                fetchedItems.add(document.toObject(UserDiaryWrite.class));
                CommunityDocumentId.add(document.getId());

                Log.e("sssss","ddddddddd");
            }


            diaryBox.addAll(fetchedItems);

            /*diaryBox.clear();
            communityadapter.setItems(fetchedItems);*/


            documentIDBox.addAll(CommunityDocumentId);



            if(diaryadapter != null){
                diaryadapter.notifyDataSetChanged();
            }
            else if(communityadapter != null){
                communityadapter.notifyDataSetChanged();
            }

            // 마지막 문서 업데이트
            int lastFetchedIndex = task.getResult().size() - 1;

            //Firestore 쿼리에서 페이징을 위한 마지막 문서를 식별하고 다음 배치 데이터를 가져오는 시작점으로 사용하는 역할
            if (lastFetchedIndex >= 0) {
                lastVisible = task.getResult().getDocuments().get(lastFetchedIndex);
            }
            progressBar.setVisibility(View.INVISIBLE);

        } else {
            Log.d("Firestore", "Error getting documents: ", task.getException());
        }
    }

    public DocumentSnapshot getDocumentSnapshot(){
        return lastVisible;
    }

    public UserDiaryWrite getListBox(int position){
        Log.e("asd","일단 클릭임");
        return diaryBox.get(position);
    }


}
