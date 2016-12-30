package beastandroiddev.rushtpo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.activities.BrotherPagerActivity;
import beastandroiddev.rushtpo.entities.Brother;
import beastandroiddev.rushtpo.utils.Constants;
import beastandroiddev.rushtpo.views.MeetABroViews.MeetABroAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MeetABroFragment extends BaseFragment implements MeetABroAdapter.BrotherClickedListener{

    @BindView(R2.id.fragment_meet_a_bro_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R2.id.fragment_meet_a_bro_progressBar)
    View mProgressBar;

    private DatabaseReference mBrothersReference;
    private ValueEventListener mBrothersListener;

    private MeetABroAdapter mAdapter;
    private ArrayList<Brother> mBrothers;

    private Unbinder mUnbinder;

    public static MeetABroFragment newInstance(){
        return new MeetABroFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meet_a_bro,container,false);
        mUnbinder = ButterKnife.bind(this,rootView);
        mBrothers = new ArrayList<>();


        String userCampus = mSharedPreference.getString(Constants.USER_CAMPUS,"");

        if (userCampus.equals("Arizona State University")){
            mBrothersReference = FirebaseDatabase.getInstance().getReference()
                    .child(Constants.FIRE_BASE_PATH_BROTHERS)
                    .child(Constants.FIRE_BASE_PATH_ASU);
        } else{
            mBrothersReference = FirebaseDatabase.getInstance().getReference()
                    .child(Constants.FIRE_BASE_PATH_BROTHERS)
                    .child(Constants.FIRE_BASE_PATH_CSU);
        }


        mAdapter = new MeetABroAdapter((BaseFragmentActivity) getActivity(),this,mProgressBar);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));

        mBrothersListener = getAllBrothers();
        mBrothersReference.addValueEventListener(mBrothersListener);
        mRecyclerView.setAdapter(mAdapter);


        return rootView;
    }

    public ValueEventListener getAllBrothers(){

        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mBrothers.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Brother brother = dataSnapshot1.getValue(Brother.class);
                    mBrothers.add(brother);
                }
                mAdapter.setmBrothers(mBrothers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
    }


    @Override
    public void OnBrotherClicked(Brother brother) {
        Intent intent = BrotherPagerActivity.newIntent(getActivity(),brother,mBrothers);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        if (mBrothersListener!=null){
            mBrothersReference.removeEventListener(mBrothersListener);
        }
    }

}
