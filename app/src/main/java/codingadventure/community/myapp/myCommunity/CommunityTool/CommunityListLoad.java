package codingadventure.community.myapp.myCommunity.CommunityTool;

import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;

public class CommunityListLoad {
    public static Query getMyCommunityQury(){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(FirebaseUtils.getCurrentUser().getEmail())
                .collection(FirebaseDBNameClass.DIARY_COLLECTION)
                .orderBy(FirebaseDBNameClass.DIARY_DOCUMENT_DATE)
                .limit(7);  // 한번에 몇개의 다이어리를 보여줄 것 인가에 대한 코드

        return query;
    }
}
