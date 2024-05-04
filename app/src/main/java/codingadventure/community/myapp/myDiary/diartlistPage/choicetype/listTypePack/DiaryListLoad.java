package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack;

import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;

public class DiaryListLoad {

    protected static Query getMyDiaryQury(){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(FirebaseUtils.getCurrentUser().getEmail())
                .collection(FirebaseDBNameClass.DIARY_COLLECTION)
                .orderBy(FirebaseDBNameClass.DIARY_DOCUMENT_DATE)
                .limit(7);  // 한번에 몇개의 다이어리를 보여줄 것인가에 대한 코드

        return query;
    }

}
