package codingadventure.community.myapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import codingadventure.community.myapp.appMainAcitivityPage.logInPage.Basics_LogInView;
import codingadventure.community.myapp.myCommunity.MainCommunity;
import codingadventure.community.myapp.myDiary.Diary_Main;

public class ChoiceDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 레이아웃 인플레이터를 사용하여 XML 레이아웃을 뷰로 변환
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.choice_dialog, null);

        ImageButton diarybutton = dialogView.findViewById(R.id.choice_diary_button);
        diarybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diary_Intent = new Intent(getContext(), Diary_Main.class);
                startActivity(diary_Intent);
                dismiss(); // 다이어로그 닫기
            }
        });

        ImageButton communitybutton = dialogView.findViewById(R.id.choice_community_button);
        communitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent community_Intent = new Intent(getContext(), MainCommunity.class);
                startActivity(community_Intent);
                dismiss(); // 다이어로그 닫기
            }
        });


        // AlertDialog.Builder를 사용하여 커스텀 레이아웃 설정
        builder.setView(dialogView);
        return builder.create();

    }
}