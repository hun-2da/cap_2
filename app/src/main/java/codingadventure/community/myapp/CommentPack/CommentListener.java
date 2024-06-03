package codingadventure.community.myapp.CommentPack;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.myCommunity.viewDiary.DiaryPage;

public class CommentListener implements OnCompleteListener<QuerySnapshot> {
    //ArrayList<DiaryCommentWrite> commentBox;

    CommentAdapter adapter;

    ImageView imageView;

    public CommentListener(/*ArrayList<DiaryCommentWrite> commentBox*/ CommentAdapter adapter, ImageView imageView) {
        //this.commentBox = commentBox;
        this.adapter = adapter;
        this.imageView = imageView;
    }

    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            ArrayList<DiaryCommentWrite> commentList = new ArrayList<>();
            for (DocumentSnapshot document : task.getResult()) {
                commentList.add(document.toObject(DiaryCommentWrite.class));
            }
            DiaryPage.commentBox.clear();
            adapter.setItems(commentList);
            adapter.notifyDataSetChanged();
            //Log.e("와이","왜 그러는 거야");
            if(commentList.size() == 0){
                Log.e("asd","             size : "+DiaryPage.commentBox.size());
                if(imageView != null)
                    imageView.setVisibility(View.VISIBLE);
            }

        }else{
            Log.w("asdf",task.getException());
        }
    }
}
