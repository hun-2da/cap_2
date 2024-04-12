package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import jp.wasabeef.richeditor.RichEditor;

/**다섯 번째 버튼 seekbar의 크기로 textSize를 조정해줄거*/
public class FifthButtonListener implements View.OnClickListener{

    RichEditor richEditor;
    FrameLayout sizeBoxLayout;
    SeekBar TextSizeSeekBar;


    public FifthButtonListener(RichEditor richEditor, FrameLayout sizeBoxLayout, SeekBar textSizeSeekBar) {
        this.richEditor = richEditor;
        this.sizeBoxLayout = sizeBoxLayout;
        TextSizeSeekBar = textSizeSeekBar;
        TextSizeSeekBar.setOnSeekBarChangeListener(new SeekBakr_SizeListener());
    }

    @Override
    public void onClick(View v) {
        if(sizeBoxLayout.getVisibility() == View.VISIBLE){
            sizeBoxLayout.setVisibility(View.INVISIBLE);
        }else if(sizeBoxLayout.getVisibility() == View.INVISIBLE){
            sizeBoxLayout.setVisibility(View.VISIBLE);
        }
    }
    /**textSize를 변경하기 위한 seekbar*/
    class SeekBakr_SizeListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            richEditor.setFontSize(progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
