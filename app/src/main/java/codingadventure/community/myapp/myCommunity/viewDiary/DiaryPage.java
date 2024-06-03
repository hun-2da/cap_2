package codingadventure.community.myapp.myCommunity.viewDiary;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myCommunity.GetCategory;
import codingadventure.community.myapp.myCommunity.MainCommunity;
import codingadventure.community.myapp.CommentPack.CommentPage;
import codingadventure.community.myapp.CommentPack.CommentSlidingPage;
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

    public static ArrayList<DiaryCommentWrite> commentBox = new ArrayList<>();

    //CommentOnCompleteListener commentListener;
    CommentPage commentPage;
    CommentSlidingPage commentAnimListener;

    Animation translateTopAnim;
    Animation translateDownAnim;

    public static FrameLayout frameLayout;

    public DiaryPage() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.community_diary_full_page, container, false);


        frameLayout = view.findViewById(R.id.communityDiary_comment_FrameLayout);
        cardView = view.findViewById(R.id.communityDiary_CategoryColor_CardView);
        categoryImageView = view.findViewById(R.id.communityDiary_CategoryDiary_imageView);
        categoryTextView = view.findViewById(R.id.communityDiary_Category_TextView);
        DateTextView = view.findViewById(R.id.communityDiary_DiaryDate_TextView);
        TitleTextView = view.findViewById(R.id.communityDiary_DiaryTitle_TextView);
        richEditor = view.findViewById(R.id.communityDiary_Content_RichEditor);
        NameTextView = view.findViewById(R.id.communityDiary_MyName_TextView);

        editText = view.findViewById(R.id.communityDiary_comment_EditText);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.e("눌려 ","ㅁㄴㅇ");


                    //query.get().addOnCompleteListener(commentListener);

                    CommentSlidingPage.CommentSwitch = false;   // 똑똑 닫혀있냐?

                    DiaryPage.frameLayout.setVisibility(View.VISIBLE);
                    frameLayout.startAnimation(translateTopAnim);

                    commentPage.setComment();

                    return true;
                }
                else{
                    Log.e("왜 안눌려 ","ㅁㄴㅇ");
                }
                return false;
            }

        });
        setCommentFragment();

        return view;
    }
    private void setAnim(){
        //commentListener = new CommentOnCompleteListener(/*commentBox*/);
        commentAnimListener = new CommentSlidingPage();


        translateTopAnim  = AnimationUtils.loadAnimation(getActivity(),R.anim.quest_diary_up);
        translateTopAnim.setAnimationListener(commentAnimListener);


        translateDownAnim = AnimationUtils.loadAnimation(requireContext(),R.anim.quest_diary_down);
        translateDownAnim.setAnimationListener(commentAnimListener);

    }
    private void setCommentFragment(){
        setAnim();

        commentPage = new CommentPage(/*commentBox,*/commentAnimListener);
        commentAnimListener.setSlidingPage(commentPage);


        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.communityDiary_comment_FrameLayout, commentPage);
        fragmentTransaction.commit();

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

    public void setDiary(UserDiaryWrite diary_data) {
        this.diary_data = diary_data;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // OnBackPressedCallback을 추가합니다.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default*/ ) {
            @Override
            public void handleOnBackPressed() {
                if(UserDiary_SlidingPage.fullCommunitySwitch)
                    if(CommentSlidingPage.CommentSwitch){
                        frameLayout.startAnimation(translateDownAnim);
                    }
                    else {
                        MainCommunity.FragmentFrameLayout.startAnimation(translateRightAnim);
                        commentPage.setRefresh();
                    }
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
