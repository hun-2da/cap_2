package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import codingadventure.community.myapp.R;


public class Login_Find extends Fragment {

    EditText email,email2,id;

    /**비밀번호 찾기를 위한 버튼*/
    Button ok_button;

    /**아이디만 찾기 위한 버튼*/
    Button check_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.login__find_fragment, container, false);

        email = view.findViewById(R.id.find_email_editText);
        email2 = view.findViewById(R.id.find_email_editext2);
        id = view.findViewById(R.id.find_id_editText);


        ok_button = view.findViewById(R.id.find_ok_Button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email2_string = email2.getText().toString();
                String id_string = id.getText().toString();
            }
        });


        check_button = view.findViewById(R.id.find_idSearch_Button);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1_string = email.getText().toString();
            }
        });

        return view;
    }


}