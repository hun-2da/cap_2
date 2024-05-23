package codingadventure.community.myapp.FirebasePack.QueryPack;

import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;

public class CommentQuery {
    public static Query getQuest(String communityDocumentID){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.COMMENT_COLLECTION)
                .document(communityDocumentID)
                .collection(FirebaseDBNameClass.COMMENT_DIARY_COMMENT)
                .orderBy(FirebaseDBNameClass.DIARY_DOCUMENT_DATE);

        return query;
    }
}
