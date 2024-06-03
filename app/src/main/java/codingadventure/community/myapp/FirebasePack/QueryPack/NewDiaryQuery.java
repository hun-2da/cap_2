package codingadventure.community.myapp.FirebasePack.QueryPack;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Touch_Constant_Name;

public class NewDiaryQuery {
    //Diary_db_Write diary;


    public NewDiaryQuery() {

    }

    public static void set_userDiary(boolean b,int tem){

        //user 컬렉션의 현재 uid에 해당하는 문서의 diary 컬렉션에 자동 생성 documentid값으로 diary지정.
        DocumentReference diaryDocument = FirebaseUtils.getFirestore().collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(FirebaseUtils.getCurrentUser().getUid())
                .collection(FirebaseDBNameClass.DIARY_COLLECTION)
                .document();

        //boolean속성인 user
        Map<String, Object> updates = getMap();
        updates.put(FirebaseDBNameClass.USER_DIARY_publicityStatus, b);
        updates.put(FirebaseDBNameClass.USER_DIARY_QUEST,1);
        Log.e("xxxx","sss"+b);


        // 위에서 지정한 도큐먼트(다이어리)에 객체를 set 성공시 && 사용자의 공개 여부가 true일때 community에도 추가
        diaryDocument
                //.set(Bubble_ClickListener.diaryDbWrite)
                .set(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        //diaryDocument.update(updates);
                        if(b) set_CommunityDiary(updates,diaryDocument,tem);
                        else {
                            Bubble_ClickListener.touch_count = Touch_Constant_Name.finish;
                            Diary_editDiary.bubble_backView.performClick();
                        }
                    }
                });    // 컬렉션에 저장을 위한 코드


    }
    private static void set_CommunityDiary(Map<String, Object> updates,DocumentReference diaryDocument,int tem){
        //Map<String, Object> updates = getMap();

        Log.e("xxxx","xxxxxxxxxxxxxx");
        updates.remove(FirebaseDBNameClass.USER_DIARY_publicityStatus);
        int limit = tem;
        updates.put(FirebaseDBNameClass.COMMUNITY_limit,limit);
        updates.put(FirebaseDBNameClass.COMMUNITY_COMMENT_COUNT,0);

        getName(updates,diaryDocument);

    }
    /**커뮤니티에 저장할 NickName*/
    private static void getName(Map<String, Object> updates,DocumentReference diaryDocument){
        DocumentReference NameDocument = FirebaseUtils.getFirestore().collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(FirebaseUtils.getCurrentUser().getUid());

        NameDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String nickName = document.getString(FirebaseDBNameClass.USER_NICKNAME);
                        updates.put(FirebaseDBNameClass.USER_NICKNAME,nickName);


                        // 커뮤니티 컬렉션에 문서 저장
                        DocumentReference communityDocument =
                                FirebaseUtils.getFirestore()
                                        .collection(FirebaseDBNameClass.COMMUNITY_COLLECTION)
                                        .document(diaryDocument.getId());

                        communityDocument
                                //.set(Bubble_ClickListener.diaryDbWrite)
                                .set(updates)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Bubble_ClickListener.touch_count = Touch_Constant_Name.finish;
                                        Diary_editDiary.bubble_backView.performClick();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // 실패시 공개 정보를 false로 바꾸어 올라가지 않은 것으로 명시
                                        boolean b = false;
                                        updates.remove(FirebaseDBNameClass.COMMUNITY_limit);
                                        updates.put(FirebaseDBNameClass.USER_DIARY_publicityStatus, b);
                                        diaryDocument.update(updates);

                                    }
                                });

                    } else {
                        System.out.println("No such document");
                    }
                } else {
                    System.out.println("Get failed with " + task.getException());
                }
            }
        });

    }
    /**다이어리와 커뮤니티의 공통부분*/
    private static Map<String, Object> getMap(){
        Map<String, Object> updates = new HashMap<>();

        updates.put(FirebaseDBNameClass.DIARY_Title, Bubble_ClickListener.diaryDbWrite.getTitle());
        updates.put(FirebaseDBNameClass.DIARY_Content, Bubble_ClickListener.diaryDbWrite.getContent());
        updates.put(FirebaseDBNameClass.DIARY_DiaryDate, Bubble_ClickListener.diaryDbWrite.getDiaryDate());
        updates.put(FirebaseDBNameClass.DIARY_Category, Bubble_ClickListener.diaryDbWrite.getCategory());

        return updates;
    }
}
