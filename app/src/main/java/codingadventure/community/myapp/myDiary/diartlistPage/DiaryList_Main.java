package codingadventure.community.myapp.myDiary.diartlistPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.myDiary.Diary_Main;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.calenderTypePack.DiaryList_CalenderType;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryList_ListType;
import codingadventure.community.myapp.myDiary.questPack.choicepack.QuestChoiceDialog;
import codingadventure.community.myapp.myDiary.questPack.choicepack.onLinePack.QuestChoiceOnLineFragment;
import codingadventure.community.myapp.myDiary.questPack.choicepack.onLinePack.QuestListAdapter;

public class DiaryList_Main extends AppCompatActivity  implements QuestChoiceDialog.MyCustomDialogListener{

    ArrayList<UserDiaryWrite> diaryBox = new ArrayList<>();
    boolean switch_visibility = true;

    public static FrameLayout blurLayout;
    public ImageButton closeButton;
    public static LinearLayout questLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diarylist_main_activity);

        blurLayout = findViewById(R.id.diarylist_blur_FrameLayout);
        closeButton = findViewById(R.id.diarylist_questList_ImageButton);
        questLinearLayout = findViewById(R.id.diarylist_questList_LinearLayout);



        ImageButton rightButton = findViewById(R.id.diarylist_main_right_button);
        ImageButton leftButton = findViewById(R.id.diarylist_main_left_button);

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

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurLayout.setVisibility(View.INVISIBLE);
                questLinearLayout.setVisibility(View.INVISIBLE);
            }
        });



    }
    @Override
    public void onOnlineButtonClick() {

        QuestListAdapter.count = 0;
        blurLayout.setVisibility(View.VISIBLE);
        questLinearLayout.setVisibility(View.VISIBLE);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.diarylist_questList_FrameLayout, new QuestChoiceOnLineFragment());
        fragmentTransaction.commit();


        Diary_Main.adapter.notifyDataSetChanged();

    }

    @Override
    public void onOfflineButtonClick() {

    }

}