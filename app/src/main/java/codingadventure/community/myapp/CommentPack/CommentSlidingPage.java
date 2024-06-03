package codingadventure.community.myapp.CommentPack;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import codingadventure.community.myapp.myCommunity.viewDiary.DiaryPage;

/**위아래로 움직일 anim 이벤트 리스너*/
public class CommentSlidingPage implements Animation.AnimationListener{
    public static boolean CommentSwitch = false;
    CommentPage commentPage;

    public void setSlidingPage(CommentPage commentPage){
        this.commentPage = commentPage;
    }

    @Override
    public void onAnimationStart(Animation animation) {

        if(!CommentSwitch){         // 닫혀있으니까 열께
            commentPage.setName();
            CommentSwitch = true;
        }else{                      // 열려있으니까 닫을께
            CommentSwitch = false;

        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(!CommentSwitch){     // 닫았으니까 끌께
            DiaryPage.frameLayout.setVisibility(View.GONE);
            Log.e("왜 ","안꺼져?");
        }else{
            // 열었으니까 기다릴께
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
