package codingadventure.community.myapp.myDiary.questPack.choicepack.onLinePack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.FirebasePack.QueryPack.UserQuestQuery;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myCommunity.viewDiary.DiaryPage;
import codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack.DiaryList_ListType;


public class QuestChoiceOnLineFragment extends Fragment{
    ArrayList<DiaryCommentWrite> questBox = new ArrayList<>();
    QuestListAdapter adapter;
    FragmentTransaction fragmentTransaction;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       ViewGroup view = (ViewGroup) inflater.inflate(R.layout.quest_choice_online_page, container, false);
        fragmentTransaction = getChildFragmentManager().beginTransaction();

        RecyclerView recyclerView = view.findViewById(R.id.online_questList_RecyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new QuestListAdapter(questBox);
        recyclerView.setAdapter(adapter);

        setQuery();


        return view;
    }
    private void setQuery(){
        Query query = UserQuestQuery.getQuestList(DiaryList_ListType.DocumentID);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<DiaryCommentWrite> questList = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        questList.add(document.toObject(DiaryCommentWrite.class));
                    }
                    adapter.clearBox();
                    adapter.setItems(questList);
                    adapter.notifyDataSetChanged();

                } else {
                    Log.e("QuestCommentBox 오류 ",task.getException()+" ");
                }
            }
        });
    }
}