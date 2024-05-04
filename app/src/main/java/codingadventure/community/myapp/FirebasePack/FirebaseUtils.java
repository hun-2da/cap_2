package codingadventure.community.myapp.FirebasePack;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtils {
    /**클라우드 스토리지 db접근용*/
    public static FirebaseFirestore getFirestore() {
        return FirebaseFirestore.getInstance();
    }

    /**계정 관련 파이어베이스*/
    public static FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }
    /**유저 캐시정보*/
    public static FirebaseUser getCurrentUser() {
        return getFirebaseAuth().getCurrentUser();
    }
}
