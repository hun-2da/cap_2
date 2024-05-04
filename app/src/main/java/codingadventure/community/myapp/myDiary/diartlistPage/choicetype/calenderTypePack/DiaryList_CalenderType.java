package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.calenderTypePack;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codingadventure.community.myapp.R;


public class DiaryList_CalenderType extends Fragment {


    public DiaryList_CalenderType() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.diary_list__calendertype_fragment, container, false);

        // 프래그먼트이기때문에 findbyid는 view.fin... 형태로 사용하면됨
        return view;
    }
}