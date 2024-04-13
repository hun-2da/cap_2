package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**title의 크기를 넘지 않게 하기 위해*/
public class TitleEditListener implements TextWatcher {
    TextView title_TtextView;

    public TitleEditListener(TextView title_TtextView) {
        this.title_TtextView = title_TtextView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int countTitle = s.length();

        title_TtextView.setText("Title ("+ s.length()+"/30)");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
