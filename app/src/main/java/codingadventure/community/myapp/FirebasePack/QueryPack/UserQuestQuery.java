package codingadventure.community.myapp.FirebasePack.QueryPack;

import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;

public class UserQuestQuery {
    public static Query getQuest(){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(FirebaseUtils.getCurrentUser().getUid())
                .collection(FirebaseDBNameClass.DIARY_COLLECTION)
                .whereNotEqualTo(FirebaseDBNameClass.DIARY_MISSION, null)
                .orderBy(FirebaseDBNameClass.DIARY_DOCUMENT_DATE);  // 한번에 몇개의 다이어리를 보여줄 것 인가에 대한 코드

        return query;
    }
}
