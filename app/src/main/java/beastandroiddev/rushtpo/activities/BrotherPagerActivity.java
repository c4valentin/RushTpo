package beastandroiddev.rushtpo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.Brother;
import beastandroiddev.rushtpo.fragments.BrotherDetailsFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BrotherPagerActivity extends AppCompatActivity {

    @BindView(R2.id.activity_brother_pager_Pager)
    ViewPager mViewPager;


    List<Brother> mBrothers;

    public static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";
    public static final String EXTRA_ALL_BROTHERS = "EXTRA_ALL_BROTHERS";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brother_pager);
        ButterKnife.bind(this);
        mBrothers = new ArrayList<>();
        mBrothers = getIntent().getParcelableArrayListExtra(EXTRA_ALL_BROTHERS);


        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Brother brother = mBrothers.get(position);
                return BrotherDetailsFragment.newInstance(brother);
            }

            @Override
            public int getCount() {
                return mBrothers.size();
            }
        });

        Brother brother = getIntent().getParcelableExtra(BROTHER_EXTRA_INFO);
        int brotherId = Integer.parseInt(brother.getBrotherId());

        for(int i =0; i<mBrothers.size();i++){
            if(Integer.parseInt(mBrothers.get(i).getBrotherId()) == brotherId){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context context, Brother brother
            ,ArrayList<Brother> brothers){
        Intent intent = new Intent(context,BrotherPagerActivity.class);
        intent.putExtra(BROTHER_EXTRA_INFO,brother);
        intent.putParcelableArrayListExtra(EXTRA_ALL_BROTHERS,brothers);
        return intent;
    }
}
