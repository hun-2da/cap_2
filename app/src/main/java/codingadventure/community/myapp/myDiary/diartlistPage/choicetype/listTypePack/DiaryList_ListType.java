package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.Diary_db_Write;


public class DiaryList_ListType extends Fragment {
    ArrayList<Diary_db_Write> diaryBox = new ArrayList<>();
    public DiaryList_ListType(ArrayList<Diary_db_Write> diaryBox){
        this.diaryBox = diaryBox;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.diarylist_listtype_fragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.listType_userDiary_RecyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        DiaryAdapter adapter = new DiaryAdapter();

        adapter.setItems(diaryBox);
        recyclerView.setAdapter(adapter);


        return view;
    }
}