package codingadventure.community.myapp.appMainAcitivityPage.logInPage.login_joinPack;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**db로 보내주기 위한 객체 (객체 자체를 전송해줄 예정) */
public class User_db_Write {
    String email;
    String password;
    String user_id;

    public User_db_Write(String email, String password, String user_id) {
        this.email = email;
        this.password = password;
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
