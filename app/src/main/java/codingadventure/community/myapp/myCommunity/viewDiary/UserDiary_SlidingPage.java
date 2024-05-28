package codingadventure.community.myapp.myCommunity.viewDiary;

import android.view.View;
import android.view.animation.Animation;

import codingadventure.community.myapp.myCommunity.MainCommunity;

public class UserDiary_SlidingPage implements Animation.AnimationListener{
    public static boolean fullCommunitySwitch = false;
    private DiaryPage diaryPage;


    public UserDiary_SlidingPage(DiaryPage diaryPage) {
        this.diaryPage = diaryPage;

    }

    @Override
    public void onAnimationStart(Animation animation) {
        if(!fullCommunitySwitch) {  //닫혀있으니까 열께
            diaryPage.setCategory();
            diaryPage.setTitle();
            diaryPage.setContent();
            diaryPage.setName();
            diaryPage.setDate();
            fullCommunitySwitch = true; // 열었어
        }
        else{   //열려있으니까 닫을께
            fullCommunitySwitch = false;    // 닫았어
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(fullCommunitySwitch){
            // 열어놨으니 닫을때 까지 아무것도 안함
        }
        else{   //닫았으니 종료
            MainCommunity.BlurConstraintLayout.setVisibility(View.GONE);
            MainCommunity.FragmentFrameLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


}
