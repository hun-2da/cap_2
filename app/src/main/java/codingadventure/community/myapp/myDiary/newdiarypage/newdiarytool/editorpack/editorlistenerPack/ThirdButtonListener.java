package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import codingadventure.community.myapp.MainActivity;
import codingadventure.community.myapp.R;
import jp.wasabeef.richeditor.RichEditor;
import yuku.ambilwarna.AmbilWarnaDialog;

public class ThirdButtonListener implements View.OnClickListener {
    private int currentColor;
    RichEditor richEditor;
    CardView textCardView;
    CardView backCardView;

    public ThirdButtonListener(RichEditor richEditor, CardView textCardView, CardView backCardView) {
        currentColor = 0xffffff;
        this.richEditor = richEditor;
        this.textCardView = textCardView;
        this.backCardView = backCardView;
    }

    @Override
    public void onClick(View v) {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(v.getContext(), currentColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // 사용자가 색상을 선택하고 "OK"를 눌렀을 때 호출됩니다.
                currentColor = color;
                choice_Button(v,color);
                // 선택된 색상으로 무언가를 업데이트 합니다.
            }
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                // 사용자가 취소 버튼을 눌렀을 때 호출됩니다.
            }
        });
        colorPicker.show();
    }
    private void choice_Button(View v,int color){
        int button_id = v.getId();
        if(button_id == R.id.editor_TextColor_Button){
            richEditor.setTextColor(color);
            textCardView.setBackgroundColor(color);
        }else if(button_id == R.id.editor_TextBackColor_Button){
            richEditor.setTextBackgroundColor(color);
            backCardView.setBackgroundColor(color);
        }
    }
}
