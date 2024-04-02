package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import codingadventure.community.myapp.R;


public class Login_Find extends Fragment {

    /**basics에서 사용된 레이아웃*/
    ConstraintLayout constraintLayout;
    EditText email,id;

    /**비밀번호 찾기를 위한 버튼*/
    Button ok_button;

    /**아이디만 찾기 위한 버튼*/
    Button check_button;

    ImageView imageView;

    public Login_Find(ConstraintLayout constraintLayout, ImageView imageView){
        this.constraintLayout = constraintLayout;
        this.imageView = imageView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.login__find_fragment, container, false);

        email = view.findViewById(R.id.find_email_editText);
        id = view.findViewById(R.id.find_id_editText);


        imageView.setImageResource(R.drawable.key_image);

        ok_button = view.findViewById(R.id.find_ok_Button);
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        Key_Check_EditText key_edit = new Key_Check_EditText(constraintLayout,imageView);
        email.setOnFocusChangeListener(key_edit);
        id.setOnFocusChangeListener(key_edit);



        return view;
    }


}