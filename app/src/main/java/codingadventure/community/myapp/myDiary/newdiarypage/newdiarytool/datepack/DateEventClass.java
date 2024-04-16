package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.datepack;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.DatePicker;

import androidx.appcompat.app.AlertDialog;

import java.util.Calendar;
import java.util.Date;

/**버전에 상관없이 해당 클레스에서 date를 처리*/
public class DateEventClass {
    Context context;

    int date_year = 0;
    int date_month = 0;

    int date_day = 0;


    public DateEventClass(Context context ) {
        this.context = context;

    }


    /**일반 적으로 보여줄 date*/
    public void getDate(DatePicker datePicker){

        int year = datePicker.getYear();
        int month = datePicker.getMonth(); // 주의: 월은 0부터 시작하므로 실제 월보다 1이 작음.
        int day = datePicker.getDayOfMonth();

        //month += 1;
        //

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day); // Calendar 월은 0부터 시작합니다.
        Date date = calendar.getTime();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            datePicker.setOnDateChangedListener(new dateEvent_Change());
        }


    }

    /**다이얼로그로 보여줄 date ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ  버전이 낮은 경우만 실행되는 메소드*/
    public void get_date_Dialog(){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        set_date_event.setDate(view,year,monthOfYear,dayOfMonth);
                        // 여기서 날짜 변경 처리
                        // 예를 들어, DatePicker의 날짜를 설정할 수 있습니다.


                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    /**changeListener 버전에따라만 적용이 가능하여 다이얼로그가 아닐때만 listner을 사용함*/
     class dateEvent_Change implements DatePicker.OnDateChangedListener{

        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int month = monthOfYear +1 ;

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("확인"); // 다이얼로그 제목 설정
            builder.setMessage(year+"년 "+month + "월 "+dayOfMonth+"일로 할까?"); // 다이얼로그 메시지 설정
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    set_date_event.setDate(view,year,monthOfYear,dayOfMonth);

                }
            }); // 확인 버튼 설정
            builder.setNegativeButton("취소", null); // 취소 버튼 설정

            // AlertDialog 생성 및 표시
            AlertDialog dialog = builder.create();
            dialog.show();

        }
    }

}
