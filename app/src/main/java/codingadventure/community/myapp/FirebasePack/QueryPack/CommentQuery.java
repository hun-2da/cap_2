package codingadventure.community.myapp.FirebasePack.QueryPack;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.myCommunity.MainCommunity;

public class CommentQuery {

    public static DocumentReference getDocument(String documentID){
        DocumentReference documentReference =
                FirebaseUtils.getFirestore()
                        .collection(FirebaseDBNameClass.COMMENT_COLLECTION)
                        .document(documentID);

        return documentReference;
    }
    public static Query getDiaryCommentQuery(DocumentReference documentReference){
        Query query =
                documentReference
                        .collection(FirebaseDBNameClass.COMMENT_DIARY_COMMENT)
                        .orderBy(FirebaseDBNameClass.COMMENT_LIKE_COUNT, Query.Direction.DESCENDING)  // 댓글 수에 대해 오름차순 정렬
                        .orderBy(FirebaseDBNameClass.COMMENT_DISLIKE_COUNT,Query.Direction.ASCENDING);


        return query;
    }

    public static void setCommunityCommentCount() {
        FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.COMMUNITY_COLLECTION)
                .document(MainCommunity.documentID)
                .update(FirebaseDBNameClass.COMMUNITY_COMMENT_COUNT, FieldValue.increment(1)).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("오류",e.toString());
                    }
                });

    }
}
