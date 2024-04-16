package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Touch_Constant_Name;

public class SALIGIAListener implements View.OnClickListener {
    String categoryName="";

    @Override
    public void onClick(View v) {

        int id = v.getId();
        int image_id = 0;
        int category_ID = 0;

        if(id == R.id.icon7_button1){
            image_id = R.drawable.icon7_pride;
            category_ID = Touch_Constant_Name.PRIDE;
            categoryName = "PRIDE";

        }else if(id == R.id.icon7_button2){
            image_id = R.drawable.icon7_greed;
            category_ID = Touch_Constant_Name.GREED;
            categoryName = "GREED";

        }else if(id == R.id.icon7_button3){
            image_id = R.drawable.icon7_lust;
            category_ID = Touch_Constant_Name.LUST;
            categoryName = "LUST";

        }else if(id == R.id.icon7_button4){
            image_id = R.drawable.icon7_envy;
            category_ID = Touch_Constant_Name.ENVY;
            categoryName = "ENVY";

        }else if(id == R.id.icon7_button5){
            image_id = R.drawable.icon7_glutth;
            category_ID = Touch_Constant_Name.GLUTTONY;
            categoryName = "GLUTTONY";

        }else if(id == R.id.icon7_button6){
            image_id = R.drawable.icon7_lath;
            category_ID = Touch_Constant_Name.WRATH;
            categoryName = "WRATH";

        }else if(id == R.id.icon7_button7){
            image_id = R.drawable.icon7_sloth;
            category_ID = Touch_Constant_Name.SLOTH;
            categoryName = "SLOTH";
        }else{

        }

        Bubble_ClickListener.diaryDbWrite.setCategory(categoryName);

        Context context = v.getContext();

        if (context instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) context;
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            NewDiaryEdit_ChoiceDialog newDiaryEditChoiceDialog = new NewDiaryEdit_ChoiceDialog(image_id,category_ID);

            newDiaryEditChoiceDialog.show(fragmentManager, "DiaryChoiceDialog");
        }

    }
}
