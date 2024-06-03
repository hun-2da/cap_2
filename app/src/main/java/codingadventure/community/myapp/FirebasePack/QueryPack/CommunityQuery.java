package codingadventure.community.myapp.FirebasePack.QueryPack;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.myCommunity.MainCommunity;

// 변경필요
public class CommunityQuery {
    public static Query getMyCommunityQuery(){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.COMMUNITY_COLLECTION)
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
    public static void deleteDocumentID(String DocumentID,boolean Quest){
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
                            FirebaseDBNameClass.COMMENT_MissionProgress, true);
        }
    }

}
