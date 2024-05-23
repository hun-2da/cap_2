package codingadventure.community.myapp.myCommunity.viewDiary;

import android.view.View;
import android.view.animation.Animation;

import codingadventure.community.myapp.myCommunity.MainCommunity;

public class UserDiary_SlidingPage implements Animation.AnimationListener{
    public static boolean fullCommunitySwitch = true;
    private DiaryPage diaryPage;


    public UserDiary_SlidingPage(DiaryPage diaryPage) {
        this.diaryPage = diaryPage;

    }

    @Override
    public void onAnimationStart(Animation animation) {
        if(fullCommunitySwitch) {
            diaryPage.setCategory();
            diaryPage.setTitle();
            diaryPage.setContent();
            diaryPage.setName();
            diaryPage.setDate();
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(!fullCommunitySwitch){
            fullCommunitySwitch = true;
            MainCommunity.BlurConstraintLayout.setVisibility(View.GONE);
            MainCommunity.FragmentFrameLayout.setVisibility(View.GONE);
        }else{
            fullCommunitySwitch = false;
            //frameLayout.setVisibility(View.VISIBLE);
            //frameLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
