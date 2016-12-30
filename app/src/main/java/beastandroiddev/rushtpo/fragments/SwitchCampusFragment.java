package beastandroiddev.rushtpo.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.activities.MainActivity;
import beastandroiddev.rushtpo.entities.Campus;
import beastandroiddev.rushtpo.utils.Constants;
import beastandroiddev.rushtpo.views.SwitchCampusViews.SwitchCampusAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SwitchCampusFragment extends BaseFragment implements SwitchCampusAdapter.CampusListener{

    @BindView(R2.id.fragment_campus_switch_recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R2.id.fragment_campus_switch_progressbar)
    View mProgressBar;

    private DatabaseReference mCampusReference;
    private ValueEventListener mCampusListener;

    private Unbinder mUnbinder;
    private SwitchCampusAdapter mAdapter;

    public static SwitchCampusFragment newInstance(){
        return new SwitchCampusFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_campus_switch, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mCampusReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIRE_BASE_PATH_CAMPUSES);
        mAdapter = new SwitchCampusAdapter((BaseFragmentActivity) getActivity(),this,mSharedPreference.getString(Constants.USER_CAMPUS,""),
                mSharedPreference.getString(Constants.USER_CAMPUS_URL,"https://dl.dropboxusercontent.com/s/oqd8rquu7l65cjn/asu.jpg?dl=0"));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCampusListener = getAllCampuses();
        mCampusReference.addValueEventListener(mCampusListener);
        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }



    private ValueEventListener getAllCampuses(){
        final List<Campus> campuses = new ArrayList<>();
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                campuses.clear();

                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    campuses.add(snapshot.getValue(Campus.class));
                }
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                mAdapter.setmCampuses(campuses);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mProgressBar.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.GONE);
            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();

        if (mCampusListener!=null){
            mCampusReference.removeEventListener(mCampusListener);
        }
    }

    @Override
    public void onCampusClicked(Campus campus) {
        mSharedPreference.edit().putString(Constants.USER_CAMPUS,campus.getCampusName()).apply();
        mSharedPreference.edit().putString(Constants.USER_CAMPUS_URL,campus.getCampusUrl()).apply();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        getActivity().finish();
    }
}
