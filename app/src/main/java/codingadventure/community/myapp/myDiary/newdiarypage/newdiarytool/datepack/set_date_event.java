package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.datepack;

import android.util.Log;
import android.widget.DatePicker;

import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;

public class set_date_event {
    public static void setDate(DatePicker view, int year, int monthOfYear, int dayOfMonth){
        //view.updateDate(year, monthOfYear, dayOfMonth);
        Log.e("","실행중");
        Bubble_ClickListener.touch_count = 3;
    }
}
