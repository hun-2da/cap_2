package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.EditorListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.FirstButtonListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.FifthButtonListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.SaveButtonListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.SecondButtonListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.SeventhButtonListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.ThirdButtonListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.SixthButtonListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack.TitleEditListener;
import jp.wasabeef.richeditor.RichEditor;

public class Editor_Initialization {
    public static int sort_count = 0;
    View view;

    RichEditor richEditor;

    TextView title_TextView;
    TextView content_TextView;

    /**editor의 onclickListener */
    EditorListener editorListener;

    /**title을 저장할 edittext*/
    EditText editText;

    //--------------------------------------------------------------------- 첫번째 버튼 객체

    /**굵기, 기울기, 밑줄, 취소선 기능을 펼쳐줄 버튼*/
    Button TextStyles_Button;

    /**버튼을 누르면 나오는 Layout Box*/
    LinearLayout TextS_BOX_Layout;

    Button Text_Bold_Button;
    Button Text_Italic_Button;
    Button Text_UnderLine_Button;
    Button Text_StrikeThrough_Button;
    //--------------------------------------------------------------------- 첫번째 버튼 객체




    //--------------------------------------------------------------------- 두번째 버튼 객체
    /**정렬 기능 버튼*/
    Button TextSort_Button;

    //--------------------------------------------------------------------- 두번째 버튼 객체





    //--------------------------------------------------------------------- 세,네 번째 버튼 객체
    /**텍스트 색상*/
    Button TextColor_Button;

    /**텍스트 배경 색상*/
    Button TextBackColor_Button;

    ThirdButtonListener thirdButtonListener;

    CardView textColor_cardView;
    CardView backColor_cardView;

    //--------------------------------------------------------------------- 세,네 번째 버튼 객체





    //--------------------------------------------------------------------- 다섯 번째 버튼 객체

    /**텍스트 사이즈 크기*/
    Button TextSize_Button;

    FrameLayout sizeBox_Layout;

    SeekBar TextSize_SeekBar;


    //--------------------------------------------------------------------- 다섯 번째 버튼 객체




    /**텍스트 리스트 만들기 버튼*/
    Button TextList_Button;

    /**숫자가 있는 텍스트 리스트 만들기 버튼*/
    Button TextNumList_Button;

    /**저장을 위한 버튼*/
    Button Save_Button;

    public Editor_Initialization(View view) {
        this.view = view;
        sort_count = 0;

    }
    public RichEditor component_Initialization(){

         richEditor = (RichEditor) view.findViewById(R.id.editor_category_richeditor);
         editText = view.findViewById(R.id.editor_Title_editText);

         title_TextView = view.findViewById(R.id.editor_title_TextView);
         content_TextView = view.findViewById(R.id.editor_content_TextView);


         editText.addTextChangedListener(new TitleEditListener(title_TextView));
         richEditor.setOnTextChangeListener(new EditorListener(richEditor,content_TextView));


         //editText.setOnClickListener(editorFocusListener);
         //--------------------------------------------------------------------- 첫번째 버튼 객체

         TextStyles_Button = view.findViewById(R.id.editor_TextStyles_Button);
         TextS_BOX_Layout = view.findViewById(R.id.editor_TextStyleBox_LinarLayout);
         Text_Bold_Button = view.findViewById(R.id.editor_TextStyles_Bold_Button);
         Text_Italic_Button = view.findViewById(R.id.editor_TextStyles_Italic_Button);
         Text_UnderLine_Button = view.findViewById(R.id.editor_TextStyles_UnderLine_Button);
         Text_StrikeThrough_Button = view.findViewById(R.id.editor_TextStyles_StrikeThrough_Button);

        TextStyles_Button.setOnClickListener(new FirstButtonListener(richEditor,TextS_BOX_Layout,Text_Bold_Button,Text_Italic_Button,Text_UnderLine_Button,Text_StrikeThrough_Button));

         //--------------------------------------------------------------------- 두번째 버튼 객체

         TextSort_Button = view.findViewById(R.id.editor_TextSort_Button);
         TextSort_Button.setOnClickListener(new SecondButtonListener(richEditor));


        //--------------------------------------------------------------------- 세번째,네번째 버튼 객체

         TextColor_Button = view.findViewById(R.id.editor_TextColor_Button);
         TextBackColor_Button = view.findViewById(R.id.editor_TextBackColor_Button);

         textColor_cardView = view.findViewById(R.id.editor_textColor_cardView);
         backColor_cardView = view.findViewById(R.id.editor_backColor_cardView);

         thirdButtonListener = new ThirdButtonListener(richEditor,textColor_cardView,backColor_cardView);

         TextColor_Button.setOnClickListener(thirdButtonListener);
         TextBackColor_Button.setOnClickListener(thirdButtonListener);


        //--------------------------------------------------------------------- 다섯 번째 버튼 객체

         TextSize_Button = view.findViewById(R.id.editor_TextSize_Button);

         sizeBox_Layout = view.findViewById(R.id.editor_TextSizeBox_LinarLayout);

         TextSize_SeekBar = view.findViewById(R.id.editor_Textsize_seekBar);

         TextSize_Button.setOnClickListener(new FifthButtonListener(richEditor,sizeBox_Layout,TextSize_SeekBar));

        //--------------------------------------------------------------------- 여섯 번째 버튼 객체
         TextList_Button = view.findViewById(R.id.editor_TextList_Button);
         TextList_Button.setOnClickListener(new SixthButtonListener(richEditor));

        //--------------------------------------------------------------------- 일곱 번째 버튼 객체
         TextNumList_Button = view.findViewById(R.id.editor_TextNumList_Button);
        TextNumList_Button.setOnClickListener(new SeventhButtonListener(richEditor));

        //--------------------------------------------------------------------- 여덟 번째 버튼 객체
         Save_Button = view.findViewById(R.id.editor_Save_Button);
        Save_Button.setOnClickListener(new SaveButtonListener(richEditor,editText));

         return richEditor;

    }
}
