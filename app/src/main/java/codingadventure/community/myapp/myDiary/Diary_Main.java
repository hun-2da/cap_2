package codingadventure.community.myapp.myDiary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.UserQuestWrite;
import codingadventure.community.myapp.FirebasePack.QueryPack.UserQuestQuery;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.myCommunity.MainCommunity;
import codingadventure.community.myapp.myDiary.diartlistPage.DiaryList_Main;
import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;
import codingadventure.community.myapp.myDiary.questPack.Quest_DiaryAdapter;
import codingadventure.community.myapp.myDiary.questPack.SlidingPageAnimationListener;

public class Diary_Main extends AppCompatActivity {
    ImageButton new_diary_Button;
    ImageButton diary_list_Button;
    ImageButton community_Button;

    RecyclerView recyclerView;
    ArrayList<UserQuestWrite> QuestDiaryBox = new ArrayList<>();
    public static Quest_DiaryAdapter adapter;
    Animation translateUpAnim;

    SlidingPageAnimationListener animationListener;

    FrameLayout frameLayout;
    public static UserQuestWrite UserQuest;



    ArrayList<String> DiaryBOX = new ArrayList<>();
    public static String DiaryDocumentID = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_main_activity);

        community_Button = findViewById(R.id.diary_community_ImageButton);
        community_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent community_Intent = new Intent(getApplicationContext(), MainCommunity.class);
                startActivity(community_Intent);
                finish();
            }
        });



        new_diary_Button = findViewById(R.id.diary_newDiary_button);
        new_diary_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_Intent = new Intent(getApplicationContext(), Diary_editDiary.class);
                startActivity(edit_Intent);
            }
        });

        diary_list_Button = findViewById(R.id.diary_diarylist_Button);
        diary_list_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_Intent = new Intent(Diary_Main.this, DiaryList_Main.class);
                startActivity(login_Intent);
            }
        });

        setAnim();
        setRecyclerView();
        setQuestAdapter();
        getQuestQuery();

    }
    private void setAnim(){
        frameLayout = findViewById(R.id.diary_commentBox_FrameLayout);

        translateUpAnim = AnimationUtils.loadAnimation(this,R.anim.quest_diary_up);
        //translateDownAnim = AnimationUtils.loadAnimation(this,R.anim.quest_diary_down);

        animationListener = new SlidingPageAnimationListener(this,QuestDiaryBox,frameLayout);
        translateUpAnim.setAnimationListener(animationListener);

    }
    private void getQuestQuery(){
        Query query = UserQuestQuery.getQuest();
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<UserQuestWrite> fetchedItems = new ArrayList<>();
                    ArrayList<String> DiaryDocumentBOX = new ArrayList<>();

                    for (DocumentSnapshot document : task.getResult()) {
                        fetchedItems.add(document.toObject(UserQuestWrite.class));
                        DiaryDocumentBOX.add(document.getId());
                    }
                    QuestDiaryBox.addAll(fetchedItems);
                    DiaryBOX.addAll(DiaryDocumentBOX);

                    adapter.notifyDataSetChanged();

                } else {
                    Log.d("Firestore", "Error getting documents: ", task.getException());
                }
            }
        });

    }
    private void setRecyclerView(){
        recyclerView = findViewById(R.id.diary_questList_recyclerView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false); // LinearLayout형태로 리싸이클러뷰를 지정

        recyclerView.setLayoutManager(layoutManager);   //layout manager을 적용

    }
    private void setQuestAdapter(){
        adapter = new Quest_DiaryAdapter(getApplicationContext(), new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                animationListener.setPosition(position);
                animationListener.setSwitch();

                frameLayout.setVisibility(View.VISIBLE);
                frameLayout.startAnimation(translateUpAnim);


                UserQuest = QuestDiaryBox.get(position);
                DiaryDocumentID = DiaryBOX.get(position);
            }
        });
        adapter.setItems(QuestDiaryBox);
        recyclerView.setAdapter(adapter);


    }




}