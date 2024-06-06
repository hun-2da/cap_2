package codingadventure.community.myapp.FirebasePack.QueryPack;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.myDiary.Diary_Main;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryList_ListType;

public class UserQuestQuery {
    public static Query getQuest(){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(FirebaseUtils.getCurrentUser().getUid())
                .collection(FirebaseDBNameClass.DIARY_COLLECTION)
                .whereNotEqualTo(FirebaseDBNameClass.DIARY_MISSION, null)
                .orderBy(FirebaseDBNameClass.DIARY_DOCUMENT_DATE);

        return query;
    }
    public static Query getQuestList(String documentID){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.COMMENT_COLLECTION)
                .document(documentID)
                .collection(FirebaseDBNameClass.COMMENT_DIARY_COMMENT)
                .orderBy(FirebaseDBNameClass.COMMENT_QUEST_PRIORITY)
                .limit(3);

        return query;
    }
    public static DocumentReference getDiaryPath(){

        DocumentReference documentReference = FirebaseUtils.getFirestore()
                    .collection(FirebaseDBNameClass.USER_COLLECTION)
                    .document(FirebaseUtils.getCurrentUser().getUid())
                    .collection(FirebaseDBNameClass.DIARY_COLLECTION)
                    .document(DiaryList_ListType.DocumentID);

        return documentReference;

    }
    public static void setQuest(DocumentReference documentReference, Map<String, Object> map, Context context){
        documentReference.update(map).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("comment오류","오류 났따ㅏㅏㅏㅏㅏ");
            }
                })
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(context, Diary_Main.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                });

    }
}
