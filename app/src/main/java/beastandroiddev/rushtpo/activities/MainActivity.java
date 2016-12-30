package beastandroiddev.rushtpo.activities;


import android.support.v4.app.Fragment;

import beastandroiddev.rushtpo.fragments.MainFragment;

public class MainActivity extends BaseFragmentActivity {

    @Override
    Fragment createFragment() {
        return MainFragment.newInstance();
    }



}
