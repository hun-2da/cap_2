package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.view.View;

import jp.wasabeef.richeditor.RichEditor;

public class SixthButtonListener implements View.OnClickListener {
    RichEditor richEditor;

    public SixthButtonListener(RichEditor richEditor) {
        this.richEditor = richEditor;
    }

    @Override
    public void onClick(View v) {
        richEditor.setBullets();
    }
}
