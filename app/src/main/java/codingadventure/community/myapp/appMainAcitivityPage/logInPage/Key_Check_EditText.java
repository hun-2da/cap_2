package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Key_Check_EditText implements View.OnFocusChangeListener {

    ConstraintLayout constraintLayout;
    ConstraintLayout.LayoutParams params;
    ImageView imageView;

    public Key_Check_EditText(ConstraintLayout constraintLayout, ImageView imageView) {
        this.constraintLayout = constraintLayout;
        this.imageView = imageView;
        params = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
    }

    public int dpToPx(int dp) {
        return (int) (dp * constraintLayout.getContext().getResources().getDisplayMetrics().density);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        int top_size_dp = 0,bottom_size_dp = 0;
        if(hasFocus){
            imageView.setVisibility(View.VISIBLE);
            top_size_dp = 100;
            bottom_size_dp = 375;
        } else {
            imageView.setVisibility(View.INVISIBLE);
            top_size_dp = 300;
            bottom_size_dp = 175;
        }
        int top_size_px = dpToPx(top_size_dp);
        int bottom_size_px = dpToPx(bottom_size_dp); // 하단 마진을 일정하게 유지

        params.setMargins(dpToPx(10), top_size_px, dpToPx(10), bottom_size_px);
        constraintLayout.setLayoutParams(params);

    }
}
