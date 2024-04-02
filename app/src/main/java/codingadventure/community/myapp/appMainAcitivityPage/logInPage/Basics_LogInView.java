package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import codingadventure.community.myapp.R;

public class Basics_LogInView extends AppCompatActivity {

    ConstraintLayout id_layout;

    /**id를 찾기위한 textview, clickListener연결되어 있음*/
    TextView find_id;

    /**id를 생성하기위한 textview, clickListener연결되어 있음*/
    TextView new_id;

    /**login 관련 객체들*/
    Login_Find loginFind;   Login_Join loginJoin;

    /**로그인 관련 layout 을 닫는 버튼*/
    Button close_button;

    /**기본 로그인 레이아웃*/
    CardView login_layout;

    /**키보드가 나왔을 때 이미지뷰*/
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_basics_activity);

        imageView = findViewById(R.id.basic_layout_ImageView);

        find_id = findViewById(R.id.find_id_textView);
        new_id = findViewById(R.id.new_user_textView);
        id_layout = findViewById(R.id.clayouto);
        login_layout = findViewById(R.id.login_cardView);

        loginFind = new Login_Find(id_layout,imageView);
        loginJoin = new Login_Join(id_layout,imageView);

        close_button = findViewById(R.id.closeButton);
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id_layout.setVisibility(View.INVISIBLE);
                login_layout.setVisibility(View.VISIBLE);
            }
        });

        id_clickListener idClickListener = new id_clickListener();
        
        find_id.setOnClickListener(idClickListener);
        new_id.setOnClickListener(idClickListener);

    }
    /**어떤 TextView를 선택했는지 확인하는 이벤트 리스너*/
    class id_clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Fragment fragment = null;

            id_layout.setVisibility(View.VISIBLE);
            login_layout.setVisibility(View.INVISIBLE);

            // FragmentTransaction을 시작하고, replace 메서드를 사용하여 Fragment 교체
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();



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