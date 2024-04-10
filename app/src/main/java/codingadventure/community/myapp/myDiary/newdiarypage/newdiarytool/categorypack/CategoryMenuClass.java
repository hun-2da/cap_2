package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.ALL_SALIGIAListener;

public class CategoryMenuClass {
    View view;

    FrameLayout bubble_layout;

    LayoutInflater layoutInflater;

    public CategoryMenuClass(View view, FrameLayout bubble_layout, LayoutInflater layoutInflater) {
        this.view = view;
        this.layoutInflater = layoutInflater;
        this.bubble_layout = bubble_layout;
        setListener();

    }
    private void setListener(){
        ALL_SALIGIAListener all_saligiaListener = new ALL_SALIGIAListener();

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
            SALIGIA[i].setOnClickListener(all_saligiaListener);
        }
       /* Button pride = view.findViewById(R.id.icon7_button1);
        pride.setOnClickListener(new PrideListener());

        Button greed = view.findViewById(R.id.icon7_button2);
        greed.setOnClickListener(new GreedListener());

        Button lust = view.findViewById(R.id.icon7_button3);
        lust.setOnClickListener(new LustListener());

        Button envy = view.findViewById(R.id.icon7_button4);
        envy.setOnClickListener(new EnvyListener());

        Button gluttony = view.findViewById(R.id.icon7_button5);
        gluttony.setOnClickListener(new Gluttony());

        Button wrath = view.findViewById(R.id.icon7_button6);
        wrath.setOnClickListener(new WrathListener());

        Button sloth = view.findViewById(R.id.icon7_button7);
        sloth.setOnClickListener(new SlothListener());*/
    }
}
