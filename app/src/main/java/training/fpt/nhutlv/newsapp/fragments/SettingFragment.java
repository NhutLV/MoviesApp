package training.fpt.nhutlv.newsapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import training.fpt.nhutlv.newsapp.R;

/**
 * Created by NhutDu on 18/12/2016.
 */

public class SettingFragment extends Fragment {
    public static Fragment newInstance(){
        Fragment fragment = new SettingFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setting,container,false);
    }
}
