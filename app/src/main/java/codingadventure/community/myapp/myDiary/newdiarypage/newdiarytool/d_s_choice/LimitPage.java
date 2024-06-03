package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.d_s_choice;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import codingadventure.community.myapp.FirebasePack.QueryPack.NewDiaryQuery;
import codingadventure.community.myapp.MainActivity;
import codingadventure.community.myapp.R;

public class LimitPage {
   /* int limit;
    View seekBarView;*/

    private SeekBar seekBar;
    private EditText editText;
    private Button button;



    public LimitPage( View seekBarView,boolean b) {

        seekBar = seekBarView.findViewById(R.id.lastpage2_seekBar);
        editText = seekBarView.findViewById(R.id.lastpage2_editTextText);
        button = seekBarView.findViewById(R.id.lastpage2_button);

        // SeekBar 값이 변경될 때 EditText에 반영
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 아무 동작도 필요 없음
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 아무 동작도 필요 없음
            }
        });

        // 버튼 클릭 시 EditText 값 가져오기
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTextValue = editText.getText().toString();
                if (!editTextValue.isEmpty()) {
                    int value = Integer.parseInt(editTextValue);
                    NewDiaryQuery.set_userDiary(b,value);

                } else {
                    Log.e("asd","dddddddddddddd");
                }
            }
        });

    }



}
