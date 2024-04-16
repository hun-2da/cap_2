package codingadventure.community.myapp.myDiary.newdiarypage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.BubbleTextViewAnimation;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;

public class Diary_editDiary extends AppCompatActivity {
    TextView bubbleTextView;        // bubble을 띄울 textVIew
    Handler handler = new Handler(Looper.getMainLooper());

    /**대화 창 뒤의 이미지뷰 클릭이벤트로 진행시켜줄꺼*/
    public static ImageView bubble_backView;

    FrameLayout bubble_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_edit_activity);


        bubbleTextView = findViewById(R.id.editdiary_bubble_TextView);
        bubble_layout = findViewById(R.id.editdiary_bubble_framelayout);

        BubbleTextViewAnimation bubble = new BubbleTextViewAnimation(handler, bubbleTextView);
        Bubble_ClickListener bubbleClick = new Bubble_ClickListener(bubble,bubble_layout,getLayoutInflater());

        bubble_backView = findViewById(R.id.editdiary_smallbackimage_imageView);
        bubble_backView.setOnClickListener(bubbleClick);

        findViewById(R.id.editdiary_back_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Diary_editDiary.this.finish();
            }
        });

    }
}