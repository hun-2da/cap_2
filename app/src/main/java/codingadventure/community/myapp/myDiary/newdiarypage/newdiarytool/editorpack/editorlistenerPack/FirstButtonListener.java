package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import jp.wasabeef.richeditor.RichEditor;

/**에디터의 첫번째 버튼 및 펼치면 나오는 버튼 들의 리스너 연결, 처리*/
public class FirstButtonListener implements View.OnClickListener {
    RichEditor richEditor;
    LinearLayout textSBoxLayout;
    Button textBoldButton;
    Button textItalicButton;
    Button textUnderLineButton;
    Button textStrikeThroughButton;

    public FirstButtonListener(RichEditor richEditor,LinearLayout textSBoxLayout, Button textBoldButton, Button textItalicButton, Button textUnderLineButton, Button textStrikeThroughButton) {
        this.richEditor = richEditor;
        this.textSBoxLayout = textSBoxLayout;
        this.textBoldButton = textBoldButton;
        this.textItalicButton = textItalicButton;
        this.textUnderLineButton = textUnderLineButton;
        this.textStrikeThroughButton = textStrikeThroughButton;
        ButtonToListener();
    }

    private void ButtonToListener() {
        textBoldButton.setOnClickListener(new BoldButtonListener());
        textItalicButton.setOnClickListener(new ItalicButtonListener());
        textUnderLineButton.setOnClickListener(new UnderLineButtonListener());
        textStrikeThroughButton.setOnClickListener(new StrikeThroughButtonListener());
    }


    @Override
    public void onClick(View v) {
        if(textSBoxLayout.getVisibility() == View.VISIBLE){
            textSBoxLayout.setVisibility(View.INVISIBLE);
        }else if(textSBoxLayout.getVisibility() == View.INVISIBLE){
            textSBoxLayout.setVisibility(View.VISIBLE);
        }
    }

    /**굵기 버튼을 처리하기위한 이벤트 리스너 클레스*/
    class BoldButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            richEditor.setBold();
        }
    }
    class ItalicButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            richEditor.setItalic();
        }
    }
    class UnderLineButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            richEditor.setUnderline();
        }
    }
    class StrikeThroughButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            richEditor.setStrikeThrough();
        }
    }

}
