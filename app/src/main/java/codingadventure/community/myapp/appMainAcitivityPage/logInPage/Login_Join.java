package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import codingadventure.community.myapp.R;


public class Login_Join extends Fragment {
    ConstraintLayout constraintLayout;
    EditText id, pass, passconfirm, email;
    Button idconfirm, confirm;

    private boolean id_check = false;

    ImageView imageView;

    FirebaseAuth mAuth;


    public Login_Join(ConstraintLayout constraintLayout, ImageView imageView,FirebaseAuth mAuth){
        this.constraintLayout = constraintLayout;
        this.imageView = imageView;
        this.mAuth = mAuth;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.login__join_fragment, container, false);

        id = view.findViewById(R.id.join_inputid_editText);
        pass = view.findViewById(R.id.join_inputpassword_editText);
        passconfirm = view.findViewById(R.id.join_inputpasswordconfirm_editText);
        email = view.findViewById(R.id.join_inputemail_editText);

        confirm = view.findViewById(R.id.join_confirm_button);

        //회원가입 확인 버튼 클릭
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("onclick확인", "시작");
                String password1 = pass.getText().toString();

                if(check_password(password1) /*&& id_check*/) {
                    String idconfirm = id.getText().toString();
                    String emailconfirm = email.getText().toString();
                    // 서버 적용
                    new_log(emailconfirm,password1);
                }
                Log.e("onclick확인", "종료");
            }
        });

        idconfirm = view.findViewById(R.id.join_idconfirm_button);

        //id 중복확인 버튼 클릭
        idconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //id_check 하는 boolean 값을 변경해 줄 수 있는 로직 작성 필요
            }
        });


        Key_Check_EditText key_edit = new Key_Check_EditText(constraintLayout,imageView);
        pass.setOnFocusChangeListener(key_edit);
        passconfirm.setOnFocusChangeListener(key_edit);
        email.setOnFocusChangeListener(key_edit);
        id.setOnFocusChangeListener(key_edit);

        imageView.setImageResource(R.drawable.key_image2);

        return view;
    }
    private void new_log(String email,String password){
        Log.e("test", "시작");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("test", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            Log.e("test", "createUserWithEmail:failure", task.getException());
                            //updateUI(null);
                        }
                    }
                });
    }

    /**비밀번호가 동일한지 확인하는 메소드*/
    private boolean check_password(String r_password) {
        Log.e("check_password", "시작");
        String password2 = pass.getText().toString();

        if(r_password.equals(password2)) {
            Log.e("check_password", "성공");
            return true;
        }
        Log.e("check_password", "실패");
        return false;
    }

}