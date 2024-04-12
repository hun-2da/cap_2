package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.Editor_Initialization;
import jp.wasabeef.richeditor.RichEditor;

/**두 번째 버튼 클릭시 글자 위치 변경*/
public class SecondButtonListener implements View.OnClickListener{
    RichEditor richEditor;
    int button_id = 0;
    public SecondButtonListener(RichEditor richEditor) {
        this.richEditor = richEditor;
    }

    @Override
    public void onClick(View v) {
        switch(Editor_Initialization.sort_count){
            case 0:
                Editor_Initialization.sort_count++;
                richEditor.setAlignCenter();
                button_id = R.drawable.richeditor_center_text_sort;
                break;
            case 1:
                Editor_Initialization.sort_count++;
                richEditor.setAlignRight();
                button_id = R.drawable.richeditor_right_text_sort;
                break;
            case 2:
                Editor_Initialization.sort_count++;
                richEditor.setAlignLeft();
                button_id = R.drawable.richeditor_left_text_sort;
                break;
            default:
                Editor_Initialization.sort_count=0;
                v.performClick();
        }
        setback_img(v,button_id);

    }
    public void setback_img(View v,int drawable_id){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(ContextCompat.getDrawable(v.getContext(), drawable_id));
        } else {
            v.setBackgroundDrawable(ContextCompat.getDrawable(v.getContext(),drawable_id));
        }
    }
}
