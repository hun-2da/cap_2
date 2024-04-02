package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import codingadventure.community.myapp.R;


public class Login_Join extends Fragment {
    ConstraintLayout constraintLayout;
    EditText id, pass, passconfirm, email;
    Button idconfirm, confirm;

    private boolean id_check = false;

    ImageView imageView;

    public Login_Join(ConstraintLayout constraintLayout, ImageView imageView){
        this.constraintLayout = constraintLayout;
        this.imageView = imageView;
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
                String password1 = pass.getText().toString();

                if(check_password(password1) && id_check) {
                    String idconfirm = id.getText().toString();
                    String emailconfirm = email.getText().toString();
                    // 서버 적용
                    
                }
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

    /**비밀번호가 동일한지 확인하는 메소드*/
    private boolean check_password(String r_password) {

        String password2 = pass.getText().toString();

        if(r_password.equals(password2)) {
            return true;
        }
        return false;
    }

}