package codingadventure.community.myapp.myCommunity.CommunityTool;

import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;

// 변경필요
public class CommunityListLoad {
    public static Query getMyCommunityQury(){
        Query query = FirebaseUtils.getFirestore()
                .collection(FirebaseDBNameClass.COMMUNITY_COLLECTION)
                .orderBy(FirebaseDBNameClass.COMMUNITY_COMMENT_COUNT, Query.Direction.DESCENDING)  // 댓글 수에 대해 오름차순 정렬
                .orderBy(FirebaseDBNameClass.COMMUNITY_DATE, Query.Direction.DESCENDING)
                .limit(7);  // 한번에 몇개의 다이어리를 보여줄 것 인가에 대한 코드

        return query;
    }
}
