package codingadventure.community.myapp.myDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import codingadventure.community.myapp.MainActivity;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.appMainAcitivityPage.logInPage.Basics_LogInView;
import codingadventure.community.myapp.myDiary.diartlistPage.DiaryList_Main;
import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;

public class Diary_Main extends AppCompatActivity {
    ImageButton new_diary_Button;
    ImageButton diary_list_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main_activity);


        new_diary_Button = findViewById(R.id.diary_newDiary_button);
        new_diary_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_Intent = new Intent(getApplicationContext(), Diary_editDiary.class);
                startActivity(edit_Intent);
            }
        });

        diary_list_Button = findViewById(R.id.diary_diarylist_Button);
        diary_list_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_Intent = new Intent(Diary_Main.this, DiaryList_Main.class);
                startActivity(login_Intent);
            }
        });

    }
}