package codingadventure.community.myapp.myCommunity.viewDiary;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.FirebasePack.QueryPack.CommentQuery;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myCommunity.GetCategory;
import codingadventure.community.myapp.myCommunity.MainCommunity;
import codingadventure.community.myapp.myCommunity.viewDiary.CommentPack.CommentOnCompleteListener;
import codingadventure.community.myapp.myCommunity.viewDiary.CommentPack.CommentPage;
import codingadventure.community.myapp.myCommunity.viewDiary.CommentPack.CommentSlidingPage;
import jp.wasabeef.richeditor.RichEditor;

public class DiaryPage extends Fragment {
    CardView cardView;
    ImageView categoryImageView;
    TextView categoryTextView;
    TextView DateTextView;
    TextView TitleTextView;
    RichEditor richEditor;
    TextView NameTextView;
    EditText editText;

    Animation translateRightAnim;

    UserDiaryWrite diary_data;
    String documentID;

    ArrayList<DiaryCommentWrite> commentBox = new ArrayList<>();

    public DiaryPage() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.community_diary_full_page, container, false);

        cardView = view.findViewById(R.id.communityDiary_CategoryColor_CardView);
        categoryImageView = view.findViewById(R.id.communityDiary_CategoryDiary_imageView);
        categoryTextView = view.findViewById(R.id.communityDiary_Category_TextView);
        DateTextView = view.findViewById(R.id.communityDiary_DiaryDate_TextView);
        TitleTextView = view.findViewById(R.id.communityDiary_DiaryTitle_TextView);
        richEditor = view.findViewById(R.id.communityDiary_Content_RichEditor);
        NameTextView = view.findViewById(R.id.communityDiary_MyName_TextView);

        editText = view.findViewById(R.id.communityDiary_comment_EditText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Query query = CommentQuery.getQuest(documentID);
                query.get().addOnCompleteListener(new CommentOnCompleteListener(commentBox));


                CommentPage commentPage = new CommentPage(commentBox);
                CommentSlidingPage commentAnimListener = new CommentSlidingPage();



                /*translateLeftAnim.setAnimationListener(communitySlidingPage);
                translateRightAnim.setAnimationListener(communitySlidingPage);

                fragment.setSlidingPage(translateRightAnim);

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.community_FrameLayout, fragment);
                fragmentTransaction.commit();*/
            }
        });



        return view;
    }
    public void setCategory(){
        String category = diary_data.getCategory();
        String color= GetCategory.getCategory_color(category);

        if (color.length() >= 1) {
            cardView.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color)));
        }
        categoryImageView.setImageResource(GetCategory.getCategory_res(category));
        categoryTextView.setText(category);
    }
    public void setDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(diary_data.getDiaryDate());
        DateTextView.setText(dateString);
    }
    public void setTitle(){
        TitleTextView.setText(diary_data.getTitle());
    }
    public void setContent(){
        richEditor.setHtml(diary_data.getContent());
    }
    public void setName(){
        NameTextView.setText(diary_data.getNickName());
    }

    public void setDiary(UserDiaryWrite diary_data,String documentID) {
        this.diary_data = diary_data;
        this.documentID = documentID;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // OnBackPressedCallback을 추가합니다.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                if(!UserDiary_SlidingPage.fullCommunitySwitch)
                    MainCommunity.FragmentFrameLayout.startAnimation(translateRightAnim);
                else{
                    setEnabled(false);
                    requireActivity().onBackPressed();
                    setEnabled(true);
                }
            }
        };

        // 프래그먼트에서 OnBackPressedDispatcher에 callback을 추가합니다.
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void setSlidingPage(Animation translateRightAnim) {
       this.translateRightAnim = translateRightAnim;
    }
}
