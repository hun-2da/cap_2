package codingadventure.community.myapp.myDiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;

public class Diary_Main extends AppCompatActivity {
    ImageButton new_diary_Button;

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

    }
}