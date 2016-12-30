package beastandroiddev.rushtpo.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import beastandroiddev.rushtpo.utils.Constants;

public class BaseFragment extends Fragment {

    protected SharedPreferences mSharedPreference;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreference = getActivity().getSharedPreferences(Constants.USER_SHARED_PREFERANCE
        , Context.MODE_PRIVATE);
    }
}
