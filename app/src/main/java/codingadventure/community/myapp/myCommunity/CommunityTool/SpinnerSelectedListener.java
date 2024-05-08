package codingadventure.community.myapp.myCommunity.CommunityTool;

import android.view.View;
import android.widget.AdapterView;

import com.google.firebase.firestore.Query;

import codingadventure.community.myapp.R;

public class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
    //protected String colorString = "";
    protected int imageRes = 0;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Query query = CommunityListLoad.getMyCommunityQury();

        switch(position){
            case 0: imageRes = R.drawable.app_icon_diary;
                break;
            case 1:
                //colorString = "#D5A7A8";
                imageRes = R.drawable.community_diaryimage_pride;
                break;
            case 2:
                imageRes = R.drawable.community_diaryimage_greed;
                //colorString = "#C6D735";
                break;
            case 3:
                imageRes = R.drawable.community_diaryimage_lust;
                break;
                //colorString = ""
            case 4:
                imageRes = R.drawable.community_diaryimage_envy;
                break;
            case 5:
                imageRes = R.drawable.community_diaryimage_gluth;
                break;
            case 6:
                imageRes = R.drawable.community_diaryimage_wrath;
                break;
            case 7:
                imageRes = R.drawable.community_diaryimage_sloth;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
