package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import codingadventure.community.myapp.R;
import jp.wasabeef.richeditor.RichEditor;

/**editor의 글자 수를 세기위한 리스너*/
public class EditorListener implements RichEditor.OnTextChangeListener {

    String message;
    RichEditor richEditor;
    TextView content_textView;
    private final int MAX_LENGTH = 300;  // 최대 글자 수 설정

    public EditorListener(RichEditor richEditor, TextView content_textView) {
        this.richEditor = richEditor;
        this.content_textView = content_textView;
        message = richEditor.getContext().getString(R.string.warning);
    }

    @Override
    public void onTextChange(String text) {
        Document doc = Jsoup.parse(text);
        String plainText = doc.text(); // HTML 태그를 제외한 순수 텍스트 추출
        if (plainText.length() > MAX_LENGTH) {
            String cutText = plainText.substring(0, MAX_LENGTH);
            richEditor.setHtml(Jsoup.parse(cutText).outerHtml()); // HTML 형식을 유지하며 텍스트 업데이트
            content_textView.setText("Content (300/300) - Max length reached");
        } else if(plainText.length() > MAX_LENGTH-10) {
            Toast.makeText(richEditor.getContext(),message,Toast.LENGTH_SHORT).show();
            content_textView.setText("Content (" + plainText.length() + "/300)");
        }else {
            content_textView.setText("Content (" + plainText.length() + "/300)");
        }
    }

}
