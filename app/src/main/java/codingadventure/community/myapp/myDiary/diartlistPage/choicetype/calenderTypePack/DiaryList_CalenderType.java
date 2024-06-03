package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.calenderTypePack;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

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

import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.R;
import jp.wasabeef.richeditor.RichEditor;

public class DiaryList_CalenderType extends Fragment {

    public DiaryList_CalenderType() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.diary_list__calendertype_fragment, container, false);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // 현재 유저의 uid를 가져옴
        String userUid = FirebaseUtils.getCurrentUser().getUid();

        // User 컬렉션의 uid 문서의 Diary 컬렉션을 참조
        CollectionReference userDiaryRef = db.collection("User").document(userUid).collection("Diary");

        // Material Calendar View 초기화
        CalendarView calendarView = view.findViewById(R.id.calendarView1);

        /** 사용자의 다이어리를 읽어오기 */
        userDiaryRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                List<Calendar> highlightedDates = new ArrayList<>();

                // Firestore에서 가져온 날짜를 Calendar 객체로 변환하여 리스트에 추가
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Date date = document.getDate("DiaryDate");

                    if (date != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        highlightedDates.add(calendar);
                    }
                }
                // 리스트를 배열로 변환
                List<Calendar> highlightedDaysList = new ArrayList<>(highlightedDates);
                // 가져온 날짜들을 캘린더에 강조시키기
                calendarView.setHighlightedDays(highlightedDaysList);

                /** 강조된 날짜에 그림 표시 */
                List<EventDay> highlightedEvents = new ArrayList<>();
                for (Calendar calendar : highlightedDates) {
                    EventDay event = new EventDay(calendar, R.drawable.check2_img); // 임시적으로 아무 이미지나 넣어둠. 원하는 이미지 넣으면 됨.
                    highlightedEvents.add(event);
                }
                calendarView.setEvents(highlightedEvents);

                /** 날짜 클릭 이벤트 처리 */
                calendarView.setOnDayClickListener(new OnDayClickListener() {
                    @Override
                    public void onDayClick(EventDay eventDay) {
                        Calendar clickedDayCalendar = eventDay.getCalendar(); //클릭한 날짜를 calendar 객체로 가져옴
                        // 클릭한 날짜로 DiaryFullPage 프래그먼트 열기
                        String clickedDate = formatDate(clickedDayCalendar.getTime());
                        OpenDiaryFullPage_Calender dialog = new OpenDiaryFullPage_Calender();
                        dialog.openDiaryFullPageFragment(userUid, clickedDate, getContext());
                    }
                });
            }
        });
        return view;
    }

    // 날짜 형식을 "yyyy-MM-dd"로 변환하는 메소드
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}