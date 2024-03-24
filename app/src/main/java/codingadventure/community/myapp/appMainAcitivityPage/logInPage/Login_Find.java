package codingadventure.community.myapp.appMainAcitivityPage.logInPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codingadventure.community.myapp.R;


public class Login_Find extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.login__find_fragment, container, false);
        return view;
    }
}