package codingadventure.community.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import codingadventure.community.myapp.appMainAcitivityPage.logInPage.Basics_LogInView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 앱을 킬때 암호 ( 잠금 ) 기능을 추가 필요로 함.

        // 여기서 만약 로그인 중 자동 로그인을 한다면 loginpage의 패키지 스킵하는 로직 추가 필요.
        //Intent choice_Intent = new Intent(MainActivity.this, Choice_Activity.class);
        //startActivity(choice_Intent);

        Intent login_Intent = new Intent(MainActivity.this, Basics_LogInView.class);
        startActivity(login_Intent);



    }
}