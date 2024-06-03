package codingadventure.community.myapp.myDiary.questPack.choicepack;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myCommunity.MainCommunity;
import codingadventure.community.myapp.myDiary.questPack.choicepack.onLinePack.QuestChoiceOnLineFragment;

public class QuestChoiceDialog extends DialogFragment {
    private MyCustomDialogListener listener;

    public interface MyCustomDialogListener {
        void onOnlineButtonClick();
        void onOfflineButtonClick();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        // 레이아웃 인플레이터를 사용하여 XML 레이아웃을 뷰로 변환
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.quest_type_choice, null);

        ImageButton onLinebutton = dialogView.findViewById(R.id.choice_online_button);

        onLinebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onOnlineButtonClick();
                }

                dismiss(); // 다이어로그 닫기
            }
        });

        ImageButton offLinebutton = dialogView.findViewById(R.id.choice_offline_button);
        offLinebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent community_Intent = new Intent(getContext(), MainCommunity.class);
                startActivity(community_Intent);*/
                dismiss(); // 다이어로그 닫기
            }
        });


        // AlertDialog.Builder를 사용하여 커스텀 레이아웃 설정
        builder.setView(dialogView);
        return builder.create();

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("이건 실행?","됨");
        try {
            listener = (MyCustomDialogListener) context;
        } catch (ClassCastException e) {
            Log.e("이게 문제당............","알겠느냐ㅑㅑㅑㅑㅑ");
            throw new ClassCastException("Calling fragment must implement MyCustomDialogListener");


        }
    }


}
