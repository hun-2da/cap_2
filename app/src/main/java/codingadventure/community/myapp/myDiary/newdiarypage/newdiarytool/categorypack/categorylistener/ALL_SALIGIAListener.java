package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import codingadventure.community.myapp.ChoiceDialog;
import codingadventure.community.myapp.R;

public class ALL_SALIGIAListener implements View.OnClickListener {

    @Override
    public void onClick(View v) {

        int id = v.getId();
        int image_id = 0;

        if(id == R.id.icon7_button1){
            image_id = R.drawable.icon7_pride;
        }else if(id == R.id.icon7_button2){
            image_id = R.drawable.icon7_greed;
        }else if(id == R.id.icon7_button3){
            image_id = R.drawable.icon7_lust;
        }else if(id == R.id.icon7_button4){
            image_id = R.drawable.icon7_envy;
        }else if(id == R.id.icon7_button5){
            image_id = R.drawable.icon7_glutth;
        }else if(id == R.id.icon7_button6){
            image_id = R.drawable.icon7_lath;
        }else if(id == R.id.icon7_button7){
            image_id = R.drawable.icon7_sloth;
        }else{

        }

        Context context = v.getContext();

        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            NewDiaryEdit_ChoiceDialog newDiaryEditChoiceDialog = new NewDiaryEdit_ChoiceDialog(image_id);

            newDiaryEditChoiceDialog.show(fragmentManager, "DiaryChoiceDialog");
        }

    }
}
