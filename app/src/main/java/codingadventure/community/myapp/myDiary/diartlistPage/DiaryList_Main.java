package codingadventure.community.myapp.myDiary.diartlistPage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.Diary_db_Write;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryList_ListType;

public class DiaryList_Main extends AppCompatActivity {
    /**계정 관련 파이어베이스*/
    FirebaseAuth mAuth;

    /**유저 캐시정보*/
    FirebaseUser currentUser;


    /**클라우드 스토리지 db접근용*/
    FirebaseFirestore firestore_db;
    ArrayList<Diary_db_Write> diaryBox = new ArrayList<>();

    Handler mainHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diarylist_main_activity);
        Log.e("일단 실행됨","흠냐링");


        firestore_db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        firestore_db.collection("User")
                .document(currentUser.getEmail()).collection("Diary")
                //.whereEqualTo("user_choice", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Diary_db_Write db_write = document.toObject(Diary_db_Write.class);  // 문서를 City 객체로 변환
                                diaryBox.add(db_write);
                                //Log.e("근데 쿼리 문제는 아니고 반복도 안함","흠냐링");
                            }
                            for (int i = 0; i < diaryBox.size(); i++) {
                                Log.e(" " +i, diaryBox.get(i).getCategory());
                            }
                            if (savedInstanceState == null) {
                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.diarlist_main_getFragment_frameLayout, new DiaryList_ListType(diaryBox))
                                        .commit();
                            }
                           /* mainHandler.post(new Runnable() {
                                @Override
                                public void run() {


                                }
                            });*/


                            //Log.e("반복하긴함","흠냐링");
                        } else {
                            //Log.e("근데 쿼리 문제","흠냐링");
                        }
                    }
                });




    }
}