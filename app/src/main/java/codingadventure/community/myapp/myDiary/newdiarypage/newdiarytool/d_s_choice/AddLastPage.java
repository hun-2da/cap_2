package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.d_s_choice;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Touch_Constant_Name;

public class AddLastPage {
    View newView;
    Button dLog_Button;
    RadioGroup choice_radioGroup;

    boolean user_choice;

    /**계정 관련 파이어베이스*/
    FirebaseAuth mAuth;

    /**유저 캐시정보*/
    FirebaseUser currentUser;


    /**클라우드 스토리지 db접근용*/
    FirebaseFirestore firestore_db;


    /**캐시에 저장되어있는 아이디 string*/
    String fieldValue ="";


    public AddLastPage(View newView) {
        this.newView = newView;
        set_component();
    }
    private void set_component(){
        user_choice = false;
        dLog_Button  = newView.findViewById(R.id.lastPage_log_Button);
        choice_radioGroup = newView.findViewById(R.id.lastPage_choice_radioGroup);


        dLog_Button.setOnClickListener(new lastButtonClickListener());
    }
    class lastButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            int radioButtonId = choice_radioGroup.getCheckedRadioButtonId();
            if(radioButtonId == R.id.lastPage_yes_radioButton){
                user_choice = true;
            }else if(radioButtonId == R.id.lastPage_no_radioButton){
                user_choice = false;
            }
            Bubble_ClickListener.diaryDbWrite.setUser_choice(user_choice);
            getDB();

        }

        /**초기화용 메소드*/
        private void getDB(){
            mAuth = FirebaseAuth.getInstance();

            currentUser = mAuth.getCurrentUser();
            firestore_db = FirebaseFirestore.getInstance();

            firestore_db.collection("User")
                    .document(currentUser.getEmail())
                    .collection("Diary")
                    .document(Bubble_ClickListener.diaryDbWrite.getTitle()+Bubble_ClickListener.diaryDbWrite.getDiary_date())
                    .set(Bubble_ClickListener.diaryDbWrite);    // 컬렉션에 저장을 위한 코드


            Bubble_ClickListener.touch_count = Touch_Constant_Name.ByeMessage;
            Diary_editDiary.bubble_backView.performClick();
        }


    }
}
