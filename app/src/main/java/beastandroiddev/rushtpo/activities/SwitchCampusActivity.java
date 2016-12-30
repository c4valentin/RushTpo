package beastandroiddev.rushtpo.activities;

import android.support.v4.app.Fragment;

import beastandroiddev.rushtpo.fragments.SwitchCampusFragment;

public class SwitchCampusActivity extends BaseFragmentActivity{
    @Override
    Fragment createFragment() {
        return SwitchCampusFragment.newInstance();
    }
}
