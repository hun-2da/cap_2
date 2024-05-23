package codingadventure.community.myapp.myCommunity.viewDiary.CommentPack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.FirestorePagingListener;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.myCommunity.CommunityTool.CommunityAdapter;

public class CommentPage extends Fragment {

    ArrayList<DiaryCommentWrite> commentBox = new ArrayList<>();

    LinearLayoutManager layoutManager;
    //CommunityAdapter
    RecyclerView recyclerView;
    EditText editText;
    TextView name_TextView;
    ImageView imageView;
    Button button;

    CommentAdapter commentAdapter;

    public CommentPage(ArrayList<DiaryCommentWrite> commentBox) {
        this.commentBox = commentBox;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.community_comment_page, container, false);

        editText = view.findViewById(R.id.commentList_Content_EditText);
        name_TextView = view.findViewById(R.id.commentList_Name_TextView);
        imageView = view.findViewById(R.id.commentList_Profile_ImageView);
        button = view.findViewById(R.id.commentList_writing_Button);


        setRecyclerView(view);


        return view;
    }

    private void setRecyclerView(ViewGroup view) {
        commentAdapter = new CommentAdapter();
        recyclerView = view.findViewById(R.id.commentList_list_RecyclerView);
        layoutManager =
                new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false); // LinearLayout형태로 리싸이클러뷰를 지정

        recyclerView.setLayoutManager(layoutManager);   //layout manager을 적용
        commentAdapter.setItems(commentBox);

        recyclerView.setAdapter(commentAdapter);
    }




}
