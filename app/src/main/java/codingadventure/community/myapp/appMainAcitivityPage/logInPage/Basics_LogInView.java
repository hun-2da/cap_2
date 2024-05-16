package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import codingadventure.community.myapp.ChoiceDialog;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.appMainAcitivityPage.logInPage.login_joinPack.Login_Join;
import codingadventure.community.myapp.appMainAcitivityPage.logInPage.loginfindPack.Login_Find;

public class Basics_LogInView extends AppCompatActivity {

    ConstraintLayout id_layout;

    /**id를 찾기위한 textview, clickListener연결되어 있음*/
    TextView find_id;

    /**id를 생성하기위한 textview, clickListener연결되어 있음*/
    TextView new_id;

    /**회원 찾기 페이지 객체*/
    Login_Find loginFind;
    /**회원 생성 페이지 객체*/
    Login_Join loginJoin;

    /**로그인 관련 layout 을 닫는 버튼*/
    Button close_button;

    /**기본 로그인 레이아웃*/
    CardView login_layout;

    /**키보드가 나왔을 때 이미지뷰*/
    ImageView imageView;

    /**ok버튼*/
    Button loginButton;

    /**아이디 창*/
    EditText id_edit;

    /**비밀번호 창*/
    EditText password_edit;

    /**파이어베이스 로그인을 위한 객체*/


    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_basics_activity);


        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();





        id_edit = findViewById(R.id.basiclogin_id_editTextText);
        password_edit = findViewById(R.id.basiclogin_password_editTextText);


        imageView = findViewById(R.id.basic_layout_ImageView);

        find_id = findViewById(R.id.find_id_textView);
        new_id = findViewById(R.id.new_user_textView);

        id_layout = findViewById(R.id.clayouto);
        login_layout = findViewById(R.id.login_cardView);

        loginFind = new Login_Find(id_layout,imageView);
        loginJoin = new Login_Join(id_layout,imageView);






        loginButton = findViewById(R.id.basiclogin_ok_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = id_edit.getText().toString().trim();
                String password = password_edit.getText().toString().trim();


                if(!email.isEmpty() && !password.isEmpty()) {


                    login_m(email,password);
                }
                /*ChoiceDialog choiceDialog = new ChoiceDialog();
                choiceDialog.show(getSupportFragmentManager(), "menuChoiceDialog");*/

            }
        });




        close_button = findViewById(R.id.closeButton);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_visibility(1);
            }
        });

        id_clickListene idClickListener = new id_clickListene();
        
        find_id.setOnClickListener(idClickListener);
        new_id.setOnClickListener(idClickListener);



    }

    private void login_m(String email,String password){
        Log.e("test","시작");
        FirebaseUtils.getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            //Toast.makeText(getApplicationContext(),"로그인 성공",Toast.LENGTH_LONG).show();
                            Log.e("tㄷst","성공");
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show(); // 실패 로직
                            //updateUI(null);
                            //Toast.makeText(getApplicationContext(),"실패",Toast.LENGTH_LONG).show();
                            Log.e("test","실패");
                        }
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = FirebaseUtils.getFirebaseAuth().getCurrentUser();
        if(FirebaseUtils.getCurrentUser() == null){
             //reload();  앱 재실행 코드
        }else{
            updateUI();
        }
    }
    private void updateUI(){

        ChoiceDialog choiceDialog = new ChoiceDialog();
        choiceDialog.show(getSupportFragmentManager(), "menuChoiceDialog");
        //layout_visibility(2);

    }
    private void layout_visibility(int i){
        switch(i){
            case 0:
                id_layout.setVisibility(View.VISIBLE);
                login_layout.setVisibility(View.INVISIBLE);
                break;
            case 1:
                id_layout.setVisibility(View.INVISIBLE);
                login_layout.setVisibility(View.VISIBLE);
                break;
            case 2:
                id_layout.setVisibility(View.INVISIBLE);
                login_layout.setVisibility(View.INVISIBLE);
                break;
        }
    }

    /**어떤 TextView를 선택했는지 확인하는 이벤트 리스너*/
    class id_clickListene implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;

            layout_visibility(0);

            // FragmentTransaction을 시작하고, replace 메서드를 사용하여 Fragment 교체


            if(v.getId() == R.id.find_id_textView){                 // 아이디 찾기를 누르면
                fragment = loginFind;
            }else if(v.getId() == R.id.new_user_textView){          // 회원가입을 누르면
                fragment = loginJoin;
            }

            transaction.replace(R.id.login_layouto, fragment);

            // 변경 사항 커밋
            transaction.commit();


        }
    }
}