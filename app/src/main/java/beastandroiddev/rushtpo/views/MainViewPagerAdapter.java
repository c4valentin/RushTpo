package beastandroiddev.rushtpo.views;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.fragments.AboutUsFragment;
import beastandroiddev.rushtpo.fragments.MeetABroFragment;
import beastandroiddev.rushtpo.fragments.RushFragment;

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private BaseFragmentActivity mActivity;
    public MainViewPagerAdapter(FragmentManager fm, BaseFragmentActivity activity) {
        super(fm);
        mActivity = activity;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment returnFragment;
        switch (position){
            case 0:
                returnFragment= AboutUsFragment.newInstance();
                break;
            case 1:
                returnFragment = MeetABroFragment.newInstance();
                break;
            case 2:
                returnFragment = RushFragment.newInstance();
                break;

            default:
                return null;
        }

        return returnFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence title;
        switch (position){
            case 0:
                title = mActivity.getString(R.string.fragment_about_us_name);
                break;
            case 1:
                title = mActivity.getString(R.string.fragment_meet_a_bro_name);
                break;
            case 2:
                title = mActivity.getString(R.string.fragment_rush_name);
                break;
            default:
                return null;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
