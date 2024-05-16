package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.datepack;

import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Touch_Constant_Name;

public class set_date_event {
    public static void setDate(DatePicker view, int year, int monthOfYear, int dayOfMonth){
        //view.updateDate(year, monthOfYear, dayOfMonth);
        Log.e("","실행중");
        Bubble_ClickListener.touch_count = Touch_Constant_Name.CATEGORY_EVENT;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date date = calendar.getTime();

        Bubble_ClickListener.diaryDbWrite.setDiaryDate(date);

        Diary_editDiary.bubble_backView.performClick();

    }
}
