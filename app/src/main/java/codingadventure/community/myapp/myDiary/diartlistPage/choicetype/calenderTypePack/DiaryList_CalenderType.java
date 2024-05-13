package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.calenderTypePack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shuhart.materialcalendarview.MaterialCalendarView;
import com.shuhart.materialcalendarview.CalendarDay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import codingadventure.community.myapp.R;


public class DiaryList_CalenderType extends Fragment {


    public DiaryList_CalenderType() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.diary_list__calendertype_fragment, container, false);


        // Material Calendar View 초기화
        MaterialCalendarView calendarView = view.findViewById(R.id.calendarView1);


        // Firestore에서 해당 유저의 일기 날짜 가져오기
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userDiaryRef = db.collection("User").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("Diary");

        // 각 문서에서 날짜 정보를 가져와서 달력에 표시
        /*
        userDiaryRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String date = document.getString("date");

                        calendarView.setDateSelected(CalendarDay.from(date), true);
                    }
                }
            }
        });
        */

        // 프래그먼트이기때문에 findbyid는 view.fin... 형태로 사용하면됨
        return view;
    }
}