package codingadventure.community.myapp.myDiary.questPack;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.UserQuestWrite;
import codingadventure.community.myapp.R;
import jp.wasabeef.richeditor.RichEditor;

public class SlidingPageAnimationListener implements Animation.AnimationListener{
    private boolean b = true;
    ArrayList<UserQuestWrite> QuestDiaryBox;
    FrameLayout frameLayout;
    View commentBoxView;
    TextView nameTextView;
    TextView commentTextView;
    RichEditor richEditor;
    Button successButton;
    Button CloseButton;

    Animation translateDownAnim;

    private int position;
    AlertDialog dialog;


    public SlidingPageAnimationListener(Activity activity, ArrayList<UserQuestWrite> QuestDiaryBox,FrameLayout frameLayout) {
        this.QuestDiaryBox = QuestDiaryBox;

        this.frameLayout = frameLayout;

        translateDownAnim = AnimationUtils.loadAnimation(activity,R.anim.quest_diary_down);
        translateDownAnim.setAnimationListener(this);


        commentBoxView = activity.findViewById(R.id.comment_box);

        nameTextView = commentBoxView.findViewById(R.id.questComment_missionWriter_Name_TextView);
        commentTextView = commentBoxView.findViewById(R.id.questComment_diaryContent_TextView);
        richEditor = commentBoxView.findViewById(R.id.questComment_MyDiary_RichEditor);

        successButton = commentBoxView.findViewById(R.id.questComment_success_Button);
        CloseButton = commentBoxView.findViewById(R.id.questComment_close_Button);

        setDiaLog(activity);

        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frameLayout.startAnimation(translateDownAnim);
            }
        });
    }
    private void setDiaLog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.MyQuest_Dialog_Title));
        builder.setMessage(activity.getString(R.string.MyQuest_Dialog_Message));


        // 버튼 추가
        builder.setPositiveButton(activity.getString(R.string.MyQuest_Dialog_OK_Button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // OK 버튼 클릭 시 실행할 코드
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(activity.getString(R.string.MyQuest_Dialog_Cansel_Button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cancel 버튼 클릭 시 실행할 코드
                dialog.dismiss();
            }
        });

        // 다이얼로그 생성 및 표시
        dialog = builder.create();
    }

    @Override
    public void onAnimationStart(Animation animation) {
        if(b) {
            UserQuestWrite quest = QuestDiaryBox.get(position);

            nameTextView.setText(quest.getMissionWriter());
            commentTextView.setText(quest.getMission());
            richEditor.setHtml(quest.getContent());

        }

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(b){
            b = false;
        }else{
            b = true;
            frameLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    public void setPosition(int position){
        this.position = position;
    }

    public void setSwitch(){b=true;}
}
