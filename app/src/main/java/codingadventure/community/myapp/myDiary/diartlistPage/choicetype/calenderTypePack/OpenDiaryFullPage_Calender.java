package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.calenderTypePack;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarDay;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnCalendarDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.questPack.choicepack.QuestChoiceDialog;
import jp.wasabeef.richeditor.RichEditor;

public class OpenDiaryFullPage_Calender {
    private FirebaseFirestore db;

    EditText titleEditText;
    RichEditor contentEditor;
    Switch publicitySwitch;
    Button questButton;


    public OpenDiaryFullPage_Calender() {
        this.db = FirebaseFirestore.getInstance();
    }

    public void openDiaryFullPageFragment(String userUid, String clickedDate, Context context) {

        CollectionReference diaryRef = db.collection(FirebaseDBNameClass.USER_COLLECTION)
                .document(userUid)
                .collection(FirebaseDBNameClass.DIARY_COLLECTION);


        // 클릭한 날짜의 시작과 끝을 나타내는 Timestamp 객체 생성
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 클릭한 날짜를 시작 시간으로 설정
            calendar.setTime(sdf.parse(clickedDate));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date startDate = calendar.getTime();

            // 클릭한 날짜의 다음 날을 끝 시간으로 설정
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Date endDate = calendar.getTime();



            // 다이어리 내용을 표시하는 다이얼로그 생성
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.diary_full_page, null);
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();

            // 다이어리 내용을 표시하는 뷰들을 찾음
            titleEditText = dialogView.findViewById(R.id.fulldiary_Title_editText);
            contentEditor = dialogView.findViewById(R.id.fulldiary_content_richeditor);
            publicitySwitch = dialogView.findViewById(R.id.fulldiary_publicityStatus_Switch);
            questButton = dialogView.findViewById(R.id.fulldiart_Quest_button);
            questButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Context context = v.getContext();
                    if (context instanceof AppCompatActivity) {
                        alertDialog.dismiss();
                        AppCompatActivity activity = (AppCompatActivity) context;
                        new QuestChoiceDialog().show(activity.getSupportFragmentManager(), "QuestChoiceDialog");
                    } else {
                        // Handle the case where context is not an AppCompatActivity
                        throw new RuntimeException("Context must be an instance of AppCompatActivity");
                    }

                }
            });



            // 쿼리를 실행하여 해당 기간에 속하는 문서 가져오기
            diaryRef.whereGreaterThanOrEqualTo(FirebaseDBNameClass.DIARY_DOCUMENT_DATE, startDate)
                    .whereLessThan(FirebaseDBNameClass.DIARY_DOCUMENT_DATE, endDate)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String title = document.getString(FirebaseDBNameClass.DIARY_Title);
                                    String content = document.getString(FirebaseDBNameClass.DIARY_Content);
                                    boolean publicityStatus = document.getBoolean(FirebaseDBNameClass.USER_DIARY_publicityStatus);


                                    // 다이어리 내용 설정
                                    titleEditText.setText(title);
                                    contentEditor.setHtml(content);
                                    publicitySwitch.setChecked(publicityStatus);

                                    // AlertDialog 생성 및 표시

                                    alertDialog.show();

                                    Log.d("MyApp", "Firestore 데이터 가져오기 성공: " + document.getData());
                                }
                            } else {
                                Log.d("MyApp", "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

