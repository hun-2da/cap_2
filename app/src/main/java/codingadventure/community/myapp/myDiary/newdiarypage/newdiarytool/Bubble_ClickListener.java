package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.CategoryMenuClass;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.datepack.DateEventClass;

public class Bubble_ClickListener implements View.OnClickListener {
    public static int touch_count;

    /** text를 animation으로 만들어 주기 위한 클레스 객체*/
    BubbleTextViewAnimation bubble;

    /**FrameLayout으로 보여줄 다른 layout*/
    FrameLayout bubble_layout;

    LayoutInflater layoutInflater;


    public Bubble_ClickListener(BubbleTextViewAnimation bubble, FrameLayout bubble_layout,LayoutInflater layoutInflater) {
        touch_count = 1;
        this.bubble = bubble;
        this.bubble_layout = bubble_layout;
        this.layoutInflater = layoutInflater;
        touch_count0();

    }

    @Override
    public void onClick(View v) {
        switch(touch_count){
            case 1:
                touch_count1(); break;
            case 3:
                touch_count3(); break;
            case 4:
                touch_count4(); break;
            default:
                return;
        }
        Log.e("touch",touch_count+" 과연 ");
        touch_count++;

        // 1. 카테고리 선택
        // 2. 탬플릿 선택
        // 3.
    }
    private void touch_count0(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click0));
    }


    /**날짜 가져 오기 페이지*/
    private void touch_count1(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click1));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            View newView = layoutInflater.inflate(R.layout.newdiary_edit_date, bubble_layout, false);
            DatePicker datePicker = newView.findViewById(R.id.diaryedit_choicedate_datepicker);
            new DateEventClass(bubble_layout.getContext()).getDate(datePicker);
            bubble_layout.addView(newView);

        }else{
            new DateEventClass(bubble_layout.getContext()).get_date_Dialog();

        }
    }
    /**카레고리 선택 페이지*/
    private void touch_count3(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click3));

        bubble_layout.removeAllViews();
        View newView = layoutInflater.inflate(R.layout.newdiary_edit_icon,bubble_layout,false);

        CategoryMenuClass categoryMenuClass = new CategoryMenuClass(newView,bubble_layout,layoutInflater);

        bubble_layout.addView(newView);
    }
    private void touch_count4(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click4));

    }

}
