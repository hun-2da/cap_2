package codingadventure.community.myapp.appMainAcitivityPage.logInPage.loginfindPack;

import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Login_password_find_Listener implements View.OnClickListener{
    FirebaseAuth mAuth;
    EditText email,id;

    public Login_password_find_Listener(FirebaseAuth mAuth, EditText email, EditText id) {
        this.mAuth = mAuth;
        this.email = email;
        this.id = id;
    }

    @Override
    public void onClick(View v) {

    }
}
