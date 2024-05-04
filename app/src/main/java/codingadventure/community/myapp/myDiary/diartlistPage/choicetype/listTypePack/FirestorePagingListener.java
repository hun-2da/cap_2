package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import codingadventure.community.myapp.myDiary.Diary_db_Write;

public class FirestorePagingListener implements OnCompleteListener<QuerySnapshot> {
    private final ArrayList<Diary_db_Write> diaryBox;
    private final DiaryAdapter adapter;

    private ProgressBar progressBar;

    private DocumentSnapshot lastVisible;

    public FirestorePagingListener(ArrayList<Diary_db_Write> diaryBox, DiaryAdapter adapter, ProgressBar progressBar) {
        this.diaryBox = diaryBox;
        this.adapter = adapter;
        this.progressBar = progressBar;
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            progressBar.setVisibility(View.VISIBLE);
            ArrayList<Diary_db_Write> fetchedItems = new ArrayList<>();
            for (DocumentSnapshot document : task.getResult()) {
                fetchedItems.add(document.toObject(Diary_db_Write.class));
            }
            diaryBox.addAll(fetchedItems);
            adapter.notifyDataSetChanged();
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


}
