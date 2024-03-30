package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import codingadventure.community.myapp.R;


public class Login_Join extends Fragment {

    EditText id, pass, passconfirm, email;
    Button idconfirm, confirm;

    private boolean id_check = false;

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
                }
            }
        });

        idconfirm = view.findViewById(R.id.join_idconfirm_button);

        //id 중복확인 버튼 클릭
        idconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

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