package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;

import java.util.Random;

import codingadventure.community.myapp.MainActivity;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.Diary_db_Write;
import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.CategoryMenuClass;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack.EnvyChoice;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack.GluttonyChoice;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack.GreedChoice;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack.LustChoice;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack.PrideChoice;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack.SlothChoice;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack.WrathChoice;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.d_s_choice.AddLastPage;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.datepack.DateEventClass;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.Editor_Initialization;
import jp.wasabeef.richeditor.RichEditor;

public class Bubble_ClickListener implements View.OnClickListener {

    /**터치 카운트는 페이지를 의미함. static인 이유는 다른 class에서 값의 변경을 시켜주기 위함. */
    public static int touch_count;

    /** text를 animation으로 만들어 주기 위한 클레스 객체*/
    BubbleTextViewAnimation bubble;

    /**FrameLayout으로 보여줄 다른 layout*/
    FrameLayout bubble_layout;

    LayoutInflater layoutInflater;


    /**database에 저장해주기 위한 객체*/
    public static Diary_db_Write diaryDbWrite;


    public Bubble_ClickListener(BubbleTextViewAnimation bubble, FrameLayout bubble_layout,LayoutInflater layoutInflater) {
        touch_count = 1;
        this.bubble = bubble;
        this.bubble_layout = bubble_layout;
        this.layoutInflater = layoutInflater;
        touch_count0();

    }

    @Override
    public void onClick(View v) {
        switch(touch_count){
            case Touch_Constant_Name.DATE_EVENT:
                touch_count1();
                break;
            case Touch_Constant_Name.CATEGORY_EVENT:
                touch_count3();
                break;
            case Touch_Constant_Name.CATEGORY_BUBBLE:
                touch_count4();
                break;
            case Touch_Constant_Name.PRIDE:
                touch_count6();
                break;
            case Touch_Constant_Name.GREED:
                touch_count8();
                break;
            case Touch_Constant_Name.LUST:
                touch_count10();
                break;
            case Touch_Constant_Name.ENVY:
                touch_count12();
                break;
            case Touch_Constant_Name.GLUTTONY:
                touch_count14();
                break;
            case Touch_Constant_Name.WRATH:
                touch_count16();
                break;
            case Touch_Constant_Name.SLOTH:
                touch_count18();
                break;
            case Touch_Constant_Name.BASICS:
                touch_count20();
                break;
            case Touch_Constant_Name.DIARLAST:
                touch_count22();
                break;
            case Touch_Constant_Name.ByeMessage:
                touch_count24();
                break;
            case Touch_Constant_Name.finish:
                touch_count25(v);
                break;

            default:
                return;
        }
        Log.e("touch",touch_count+" 과연 ");
        touch_count++;

        // 1. 카테고리 선택
        // 2. 탬플릿 선택
        // 3.
    }
    private void touch_count0(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click0));
        diaryDbWrite = new Diary_db_Write("","",null,"",false);
        //bubble_layout.getViewTreeObserver().addOnGlobalLayoutListener(new Editor_KeyboardListener(bubble_layout));   // 키보드 로직 리스너로 연결
    }


    /**실질적 1 번의 터치 페이지 _ 날짜 가져 오기 페이지*/
    private void touch_count1(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click1));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            View newView = layoutInflater.inflate(R.layout.newdiary_edit_date, bubble_layout, false);
            DatePicker datePicker = newView.findViewById(R.id.diaryedit_choicedate_datepicker);
            new DateEventClass(bubble_layout.getContext()).getDate(datePicker);
            bubble_layout.addView(newView);

        }else{
            new DateEventClass(bubble_layout.getContext()).get_date_Dialog();

        }
    }
    /**실질적 2번의 터치 페이지_ 카레고리 선택 페이지*/
    private void touch_count3(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click3));

        bubble_layout.removeAllViews();
        View newView = layoutInflater.inflate(R.layout.newdiary_edit_icon,bubble_layout,false);
        new CategoryMenuClass(newView,bubble_layout,layoutInflater);

        bubble_layout.addView(newView);
    }
    /**실질적 3번의 터치 페이지 _ 대화만 바뀌는 메소드*/
    private void touch_count4(){
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_click4));

    }

    /*실질적 4번의 터치 페이지 _ 사용자가 pride를 선택했을 때 호출될 메소드 **/
    private void touch_count6(){
        PrideChoice pridePage = new PrideChoice(
                "",
                "",
                "",
                "",
                load_editor()
        );

    }

    /*실질적 4번의 터치 페이지 _ 사용자가 greedPage를 선택했을 때 호출될 메소드 **/
    private void touch_count8(){
        GreedChoice greedPage = new GreedChoice(
                "",
                "",
                "",
                "",
                load_editor()
        );
    }

    /**실질적 4번의 터치 페이지 _ 사용자가 lustPage를 선택했을 때 호출될 메소드*/
    private void touch_count10(){
        LustChoice lustPage = new LustChoice(
                "",
                "",
                "",
                "",
                load_editor()
        );
    }

    /**실질적 4번의 터치 페이지 _ 사용자가 envyPage를 선택했을 때 호출될 메소드 */
    private void touch_count12(){
        EnvyChoice envyPage = new EnvyChoice(
                "",
                "",
                "",
                "",
                load_editor()
        );
    }

    /**실질적 4번의 터치 페이지 _ 사용자가 gluttonyPage를 선택했을 떄 호출될 메소드 */
    private void touch_count14(){
        GluttonyChoice gluttonyPage = new GluttonyChoice(
                "",
                "",
                "",
                "",
                load_editor()
        );
    }

    /**실질적 4번의 터치 페이지 _ 사용자가 wrathPage를 선택 했을 때 호출될 메소드*/
    private void touch_count16(){
        WrathChoice wrathPage = new WrathChoice(
                "",
                "",
                "",
                "",
                load_editor()
        );
    }

    /**실질적 4번의 터치 페이지 _ 사용자가 slothPage를 선택 했을 때 호출될 메소드*/
    private void touch_count18(){

        SlothChoice slothChoice = new SlothChoice(
                "",
                "",
                "",
                "",
                load_editor()
        );
    }

    /**실질적 4번의 터치 페이지 _ 사용자가 BASICS을 선택 했을 때 호출될 메소드*/
    private void touch_count20(){
        load_editor();
    }
    /**Layout Inplater해서 FrameLayout에다가 editor.xml을 띄울 메소드*/


    private void touch_count22(){
        bubble_layout.removeAllViews();
        View newView = layoutInflater.inflate(R.layout.newdiary_edit_lastpage,bubble_layout,false);
        new AddLastPage(newView);
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_lastclick));
        bubble_layout.addView(newView);
    }
    private void touch_count24(){
        bubble_layout.removeAllViews();
        bubble.animationText(bubble_layout.getContext().getString(R.string.Bubble_ClickListener_lastclick2));
    }
    private void touch_count25(View v){
        Context context = v.getContext();
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    private RichEditor load_editor(){
        bubble_layout.removeAllViews();
        randomBubble();
        View newView = layoutInflater.inflate(R.layout.newdiary_edit_editor,bubble_layout,false);
        RichEditor richEditor = new Editor_Initialization(newView).component_Initialization();

        bubble_layout.addView(newView);
        return richEditor;
    }


    private void randomBubble(){
        int randomIntBounded = new Random().nextInt(8);
        int choice_bubble = 0;
        switch(randomIntBounded){
            case 0:
                choice_bubble = R.string.Bubble_ClickListener_click6_0; break;
            case 1:
                choice_bubble = R.string.Bubble_ClickListener_click6_1; break;
            case 2:
                choice_bubble = R.string.Bubble_ClickListener_click6_2; break;
            case 3:
                choice_bubble = R.string.Bubble_ClickListener_click6_3; break;
            case 4:
                choice_bubble = R.string.Bubble_ClickListener_click6_4; break;
            case 5:
                choice_bubble = R.string.Bubble_ClickListener_click6_5; break;
            case 6:
                choice_bubble = R.string.Bubble_ClickListener_click6_6; break;
            case 7:
                choice_bubble = R.string.Bubble_ClickListener_click6_7; break;
        }
        bubble.animationText(bubble_layout.getContext().getString(choice_bubble));
    }
}
