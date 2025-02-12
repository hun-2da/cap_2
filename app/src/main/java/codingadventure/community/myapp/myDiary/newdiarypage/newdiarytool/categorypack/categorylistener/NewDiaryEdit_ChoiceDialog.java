package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.newdiarypage.Diary_editDiary;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;
import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Touch_Constant_Name;

public class NewDiaryEdit_ChoiceDialog extends DialogFragment {
    int image_id = 0;
    int category_id = 0;

    public NewDiaryEdit_ChoiceDialog( int image_id ,int category_id) {
        this.image_id = image_id;
        this.category_id = category_id;
    }


  /*
        @Override
        public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog != null) {
            Log.e("ㅜㅕㅣㅣ","null이었지요");
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();

                params.width = ViewGroup.LayoutParams.WRAP_CONTENT; // 너비 내용에 맞게 설정
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT; // 높이 내용에 맞게 설정
                window.setAttributes(params);
            }
        }
    }*/

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.CustomDialogStyle);

        // 레이아웃 인플레이터를 사용하여 XML 레이아웃을 뷰로 변환
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.newdiary_edit_choice_dialog, null);


        ImageButton basicsbutton = dialogView.findViewById(R.id.newdiary_edit_Button1);
        basicsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bubble_ClickListener.touch_count = Touch_Constant_Name.BASICS;
                Diary_editDiary.bubble_backView.performClick();



                dismiss(); // 다이어로그 닫기
            }
        });

        ImageButton tamplatebutton = dialogView.findViewById(R.id.newdiary_edit_Button2);

        tamplatebutton.setImageResource(image_id);
        tamplatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bubble_ClickListener.touch_count = category_id;
                Diary_editDiary.bubble_backView.performClick();



                dismiss(); // 다이어로그 닫기
            }
        });

        // AlertDialog.Builder를 사용하여 커스텀 레이아웃 설정
        builder.setView(dialogView);
        return builder.create();
    }
}
