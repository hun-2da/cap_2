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

public class User_auth_Write {
    FirebaseFirestore firestore_db;
    FirebaseAuth mAuth;

    public User_auth_Write(FirebaseFirestore firestore_db, FirebaseAuth mAuth) {
        this.firestore_db = firestore_db;
        this.mAuth = mAuth;
    }

    /**Authentication로 보내기위한 메소드*/
    protected void new_log(Activity activity,String email, String password,String user_id){
        Log.e("test", "시작");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("test", "createUserWithEmail:success");
                            mAuth.getCurrentUser();
                            User_db_Write join_id = new User_db_Write(email,password,user_id);
                            firestore_db.collection("User").document(email).set(join_id);
                            //updateUI(user);
                        } else {
                            Log.e("test", "createUserWithEmail:failure", task.getException());
                            //updateUI(null);
                        }
                    }
                });
    }
}
