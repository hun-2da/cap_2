package codingadventure.community.myapp.myCommunity.viewDiary.CommentPack;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.FirebaseUtils;
import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.FirebasePack.QueryPack.CommentQuery;
import codingadventure.community.myapp.FirebasePack.QueryPack.CommunityQuery;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myCommunity.MainCommunity;
import codingadventure.community.myapp.myCommunity.viewDiary.DiaryPage;

/**full 댓글 페이지 (프래그 먼트)*/
public class CommentPage extends Fragment {

    //ArrayList<DiaryCommentWrite> commentBox = new ArrayList<>();

    CommentSlidingPage commentAnimListener;

    LinearLayoutManager layoutManager;
    //CommunityAdapter
    RecyclerView recyclerView;
    EditText editText;
    TextView name_TextView;
    ImageView imageView;
    ImageView CommentImageView;
    ImageButton button;

    CommentAdapter commentAdapter;


    Animation translateDownAnim;

    DocumentReference documentReference;


    public CommentPage(/*ArrayList<DiaryCommentWrite> commentBox,*/   CommentSlidingPage commentAnimListener) {
        //this.commentBox = commentBox;
        this.commentAnimListener = commentAnimListener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.community_comment_page, container, false);

        editText = view.findViewById(R.id.commentList_Content_EditText);

        name_TextView = view.findViewById(R.id.commentList_Name_TextView);

        imageView = view.findViewById(R.id.commentList_Profile_ImageView);
        CommentImageView = view.findViewById(R.id.commentList_NoComment_ImageView);

        button = view.findViewById(R.id.commentList_writing_ImageButton);

        setRecyclerView(view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getContent = editText.getText().toString();
                editText.setText("");
                if(!getContent.trim().equals("")){

                            documentReference.update(FirebaseDBNameClass.COMMUNITY_COMMENT_COUNT, FieldValue.increment(1))
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    documentReference.collection(FirebaseDBNameClass.COMMENT_DIARY_COMMENT)
                                            .document()
                                            .set(new DiaryCommentWrite(
                                                    new Date(),
                                                    MainCommunity.NickName,
                                                    getContent,
                                                    0,
                                                    0
                                            )).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    //commentAdapter.notifyDataSetChanged();
                                                    CheckCommentDocument();
                                                    CommentImageView.setVisibility(View.INVISIBLE);
                                                    CommentQuery.setCommunityCommentCount();

                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Snackbar.make(
                                                            view,
                                                            view.getContext().getString(R.string.comment_SnackBar_fail),
                                                            Snackbar.LENGTH_SHORT
                                                    ).show();
                                                }
                                            });
                                }
                            });
                }
            }
        });

        setCloseAnim();
        //setBackButton(view);

        return view;
    }
    public void setComment(){
        documentReference = CommentQuery.getDocument(MainCommunity.documentID);
        CheckCommentDocument();

    }

    private void CheckCommentDocument() {
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if(!task.getResult().exists()){
                        Map<String, Object> updates = new HashMap<>();
                        updates.put(FirebaseDBNameClass.COMMUNITY_COMMENT_COUNT,0);
                        documentReference.set(updates);
                    }
                    CommentQuery.getDiaryCommentQuery(documentReference)
                            .get()
                            .addOnCompleteListener(new CommentListener(/*commentBox*/commentAdapter,CommentImageView));

                    commentAdapter.notifyDataSetChanged();

                }
            }
        });
    }

    private void setCloseAnim() {
        translateDownAnim  = AnimationUtils.loadAnimation(getContext(),R.anim.quest_diary_down);
        translateDownAnim.setAnimationListener(commentAnimListener);

    }
    public void setName(){
        name_TextView.setText(MainCommunity.NickName);
    }

    private void setRecyclerView(ViewGroup view) {
        commentAdapter = new CommentAdapter();
        recyclerView = view.findViewById(R.id.commentList_list_RecyclerView);
        layoutManager =
                new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false); // LinearLayout형태로 리싸이클러뷰를 지정

        recyclerView.setLayoutManager(layoutManager);   //layout manager을 적용
        commentAdapter.setItems(DiaryPage.commentBox);

        recyclerView.setAdapter(commentAdapter);


    }
    public void setRefresh(){
        CommentImageView.setVisibility(View.INVISIBLE);
        //DiaryPage.commentBox.clear();
        commentAdapter.notifyDataSetChanged();
        //commentAdapter.clearData();
    }




}
