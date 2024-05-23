package codingadventure.community.myapp.myCommunity.viewDiary.CommentPack;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;

public class CommentOnCompleteListener implements OnCompleteListener {

    ArrayList<DiaryCommentWrite> commentBox;

    public CommentOnCompleteListener(ArrayList<DiaryCommentWrite> commentBox) {
        this.commentBox = commentBox;
    }

    @Override
    public void onComplete(@NonNull Task task) {
        if (task.isSuccessful()) {
            ArrayList<DiaryCommentWrite> fetchedItems = new ArrayList<>();

            QuerySnapshot querySnapshot = (QuerySnapshot) task.getResult(); // 명시적으로 QuerySnapshot으로 캐스팅
            for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                fetchedItems.add(document.toObject(DiaryCommentWrite.class));
            }
            commentBox.addAll(fetchedItems);
            //adapter.notifyDataSetChanged();

        }else{
            Log.w("버그 데수", "Error getting documents.", task.getException());
        }


    }
}
