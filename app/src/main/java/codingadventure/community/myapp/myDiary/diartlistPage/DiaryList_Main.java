package codingadventure.community.myapp.myDiary.diartlistPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.calenderTypePack.DiaryList_CalenderType;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryList_ListType;

public class DiaryList_Main extends AppCompatActivity {

    ArrayList<UserDiaryWrite> diaryBox = new ArrayList<>();
    boolean switch_visibility = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diarylist_main_activity);
        Log.e("일단 실행됨","흠냐링");

        Button rightButton = findViewById(R.id.diarylist_main_right_button);
        Button leftButton = findViewById(R.id.diarylist_main_left_button);

        DiaryList_ListType listType = new DiaryList_ListType(rightButton);
        DiaryList_CalenderType calenderType = new DiaryList_CalenderType();



        getSupportFragmentManager().beginTransaction()
                .add(R.id.diarylist_main_getFragment_frameLayout,listType)
                .add(R.id.diarylist_main_getFragment_frameLayout, calenderType)
                .hide(calenderType)
                .show(listType)
                .commit();


        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                .hide(listType)
                .show(calenderType)
                .commit();

                leftButton.setVisibility(View.VISIBLE);
                rightButton.setVisibility(View.INVISIBLE);
            }
        });


        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .hide(calenderType)
                        .show(listType)
                        .commit();


                leftButton.setVisibility(View.INVISIBLE);
                rightButton.setVisibility(View.VISIBLE);
            }
        });



    }
}