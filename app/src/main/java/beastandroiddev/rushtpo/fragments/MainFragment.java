package beastandroiddev.rushtpo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;



import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.activities.SwitchCampusActivity;
import beastandroiddev.rushtpo.utils.Constants;
import beastandroiddev.rushtpo.views.MainViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment {

    @BindView(R2.id.fragment_main_tabLayout)
    TabLayout mTabLayout;

    @BindView(R2.id.fragment_main_viewPager)
    ViewPager mViewPager;

    private Unbinder mUnbinder;

    public static MainFragment newInstance(){
        return new MainFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        mUnbinder = ButterKnife.bind(this,rootView);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getActivity().getSupportFragmentManager(),
                (BaseFragmentActivity) getActivity());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (((BaseFragmentActivity)getActivity()).getSupportActionBar()!=null){

            ((BaseFragmentActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolBarHeader);
            ((BaseFragmentActivity)getActivity()).getSupportActionBar().setSubtitle(mSharedPreference.getString(
                    Constants.USER_CAMPUS,""));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_switch:
                Intent intent = new Intent(getActivity(), SwitchCampusActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


}
