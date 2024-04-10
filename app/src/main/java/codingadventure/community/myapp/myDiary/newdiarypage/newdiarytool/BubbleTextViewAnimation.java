package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

public class BubbleTextViewAnimation {

    private Handler handler;
    private TextView bubbleTextView;

    private final int time = 100;

    public BubbleTextViewAnimation(Handler handler, TextView bubbleTextView) {
        this.handler = handler;
        this.bubbleTextView = bubbleTextView;
    }
    public void animationText(String message){

        new Thread(new Runnable() {
            @Override
            public void run() {
                textAni(message);
            }
        }).start();

    }
    private void textAni(String message){
        for (int i = 0; i < message.length(); i++) {
            final int finalI = i;
            if(handler == null) { return;}
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bubbleTextView.setText(message.substring(0, finalI + 1));

                }
            }, time * i);  // 문자를 입력하는 속도를 조절
        }
    }
}
