package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.SALIGIAListener;


public class CategoryMenuClass {
    View view;

    FrameLayout bubble_layout;

    LayoutInflater layoutInflater;

    /**Category의 버튼 component들의 id값을 연결 및 onclicklistener과 연결해주는 클레스*/
    public CategoryMenuClass(View view, FrameLayout bubble_layout, LayoutInflater layoutInflater) {
        this.view = view;
        this.layoutInflater = layoutInflater;
        this.bubble_layout = bubble_layout;
        setListener();

    }
    /**버튼 연결 및 초기화를 위한 메소드 + Listener연결 */
    private void setListener(){
        SALIGIAListener saligiaListener = new SALIGIAListener();

        Button SALIGIA[] = new Button[7];
        for(int i =0; i<7; i++){
            switch(i){
                case 0:
                    SALIGIA[i] = view.findViewById(R.id.icon7_button1);
                    break;
                case 1:
                    SALIGIA[i] = view.findViewById(R.id.icon7_button2);
                    break;
                case 2:
                    SALIGIA[i] = view.findViewById(R.id.icon7_button3);
                    break;
                case 3:
                    SALIGIA[i] = view.findViewById(R.id.icon7_button4);
                    break;
                case 4:
                    SALIGIA[i] = view.findViewById(R.id.icon7_button5);
                    break;
                case 5:
                    SALIGIA[i] = view.findViewById(R.id.icon7_button6);
                    break;
                case 6:
                    SALIGIA[i] = view.findViewById(R.id.icon7_button7);
                    break;
            }
            SALIGIA[i].setOnClickListener(saligiaListener);
        }
    }


}
