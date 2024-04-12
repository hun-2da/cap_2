package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.view.View;
import android.widget.EditText;

import jp.wasabeef.richeditor.RichEditor;


/**저장버튼, html코드를 가져오기위한 버튼*/

public class SaveButtonListener implements View.OnClickListener {
    RichEditor richEditor;
    EditText editText;

    public SaveButtonListener(RichEditor richEditor, EditText editText) {
        this.richEditor = richEditor;
        this.editText = editText;
    }

    @Override
    public void onClick(View v) {
        String title = editText.getText().toString();
        String component = richEditor.getHtml();

    }
}
