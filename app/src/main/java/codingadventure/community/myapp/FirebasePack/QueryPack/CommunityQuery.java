package codingadventure.community.myapp.FirebasePack.QueryPack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.myCommunity.MainCommunity;
import codingadventure.community.myapp.myDiary.Diary_Main;

// 변경필요
public class CommunityQuery {
    public static Query getMyCommunityQuery(long Temperature){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.COMMUNITY_COLLECTION)
                .whereLessThanOrEqualTo(FirebaseDBNameClass.COMMUNITY_limit, Temperature)
                .orderBy(FirebaseDBNameClass.COMMUNITY_COMMENT_COUNT, Query.Direction.ASCENDING)  // 댓글 수에 대해 오름차순 정렬
                .orderBy(FirebaseDBNameClass.COMMUNITY_DATE, Query.Direction.DESCENDING)
                .limit(7);  // 한번에 몇개의 다이어리를 보여줄 것 인가에 대한 코드

        return query;
    }
    public static DocumentReference getMyName(){
        DocumentReference Document = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(FirebaseUtils.getCurrentUser().getUid());

        return Document;
    }
    public static void deleteDocumentID(String DocumentID, boolean Quest, Context context){
        FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.COMMUNITY_COLLECTION)
                .document(DocumentID)
                .delete();

        if(Quest) {
            FirebaseUtils.getFirestore()
                    .collection(FirebaseDBNameClass.USER_COLLECTION)
                    .document(FirebaseUtils.getFirebaseAuth().getUid())
                    .collection(FirebaseDBNameClass.DIARY_COLLECTION)
                    .document(DocumentID)
                    .update(FirebaseDBNameClass.USER_DIARY_publicityStatus, false,
                            FirebaseDBNameClass.COMMENT_MissionProgress, true,
                            FirebaseDBNameClass.USER_DIARY_QUEST,1)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                            Intent intent = new Intent(context, Diary_Main.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);

                        }
                    });
        }
    }

}
