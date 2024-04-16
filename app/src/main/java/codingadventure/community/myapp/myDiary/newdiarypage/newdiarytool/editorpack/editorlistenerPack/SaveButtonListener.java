package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;
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
        String content = richEditor.getHtml();

        Bubble_ClickListener.diaryDbWrite.setTitle(title);
        Bubble_ClickListener.diaryDbWrite.setContent(content);

        Bubble_ClickListener.touch_count = 22;
        Diary_editDiary.bubble_backView.performClick();
    }

}
