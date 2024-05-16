package codingadventure.community.myapp.appMainAcitivityPage.logInPage.login_joinPack;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;

public class User_auth_Write {


    public User_auth_Write() {

    }

    /**Authentication로 보내기위한 메소드*/
    protected void new_log(Activity activity,String email, String password,String user_id){

        FirebaseUtils.getFirebaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUtils.getCurrentUser();

                            //User_db_Write join_id = new User_db_Write(email,password,user_id);

                            HashMap updates = new HashMap<>();
                            updates.put("NickName",user_id);

                            FirebaseUtils.getFirestore()
                                    .collection(FirebaseDBNameClass.USER_COLLECTION)
                                    .document(FirebaseUtils.getCurrentUser().getUid())
                                    .set(updates);

                        } else {
                            Log.e("test", "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }
}
