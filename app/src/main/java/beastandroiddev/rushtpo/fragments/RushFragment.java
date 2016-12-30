package beastandroiddev.rushtpo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import beastandroiddev.rushtpo.activities.CampusMapActivity;
import beastandroiddev.rushtpo.activities.MapActivity;
import beastandroiddev.rushtpo.entities.RushEvent;
import beastandroiddev.rushtpo.utils.Constants;
import beastandroiddev.rushtpo.views.RushViews.Item;
import beastandroiddev.rushtpo.views.RushViews.RushEventAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RushFragment extends BaseFragment implements RushEventAdapter.RushEventListener {

    @BindView(R2.id.fragment_rush_recyclerView)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;


    private Item mInformationalEvents;
    private Item mCommunityEvents;
    private Item mSocialEvents;

    private DatabaseReference mInformationalEventsReference;
    private ValueEventListener mInformationalEventsListener;


    private DatabaseReference mSocialEventsReference;
    private ValueEventListener mSocialEventsListener;

    private DatabaseReference mCommunityEventsReference;
    private ValueEventListener mCommunityEventsListener;



    public static RushFragment newInstance(){
        return new RushFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rush,container,false);
        mUnbinder = ButterKnife.bind(this,rootView);
        mRecyclerView.scrollToPosition(0);
        RushEventAdapter adapter = new RushEventAdapter((BaseFragmentActivity)getActivity(),
                this,mSharedPreference.getString(Constants.USER_CAMPUS,""));

        List<Item> data = new ArrayList<>();
        mInformationalEvents = new Item(RushEventAdapter.HEADER,getString(R.string.informational));
        mInformationalEvents.invisibleChildren = new ArrayList<>();

        mCommunityEvents = new Item(RushEventAdapter.HEADER,getString(R.string.service));
        mCommunityEvents.invisibleChildren = new ArrayList<>();

        mSocialEvents = new Item(RushEventAdapter.HEADER,getString(R.string.social));
        mSocialEvents.invisibleChildren = new ArrayList<>();



        if (mSharedPreference.getString(Constants.USER_CAMPUS,"").equals("Arizona State University")){
            mInformationalEventsReference = FirebaseDatabase.getInstance()
                    .getReference()
                    .child(Constants.FIRE_BASE_PATH_RUSH_EVENTS)
                    .child(Constants.FIRE_BASE_PATH_ASU)
                    .child(Constants.FIRE_BASE_PATH_RUSH_INFORMATIONALS);

            mSocialEventsReference = FirebaseDatabase.getInstance()
                    .getReference()
                    .child(Constants.FIRE_BASE_PATH_RUSH_EVENTS)
                    .child(Constants.FIRE_BASE_PATH_ASU)
                    .child(Constants.FIRE_BASE_PATH_RUSH_SOCIALS);

            mCommunityEventsReference = FirebaseDatabase.getInstance()
                    .getReference()
                    .child(Constants.FIRE_BASE_PATH_RUSH_EVENTS)
                    .child(Constants.FIRE_BASE_PATH_ASU)
                    .child(Constants.FIRE_BASE_PATH_RUSH_SERVICES);
        } else {

            mInformationalEventsReference = FirebaseDatabase.getInstance()
                    .getReference()
                    .child(Constants.FIRE_BASE_PATH_RUSH_EVENTS)
                    .child(Constants.FIRE_BASE_PATH_CSU)
                    .child(Constants.FIRE_BASE_PATH_RUSH_INFORMATIONALS);

            mSocialEventsReference = FirebaseDatabase.getInstance()
                    .getReference()
                    .child(Constants.FIRE_BASE_PATH_RUSH_EVENTS)
                    .child(Constants.FIRE_BASE_PATH_CSU)
                    .child(Constants.FIRE_BASE_PATH_RUSH_SOCIALS);

            mCommunityEventsReference = FirebaseDatabase.getInstance()
                    .getReference()
                    .child(Constants.FIRE_BASE_PATH_RUSH_EVENTS)
                    .child(Constants.FIRE_BASE_PATH_CSU)
                    .child(Constants.FIRE_BASE_PATH_RUSH_SERVICES);
        }



        mInformationalEventsListener = getInformationEvents();
        mCommunityEventsListener = getServiceEvents();
        mSocialEventsListener = getSocialEvents();

        mInformationalEventsReference.addValueEventListener(mInformationalEventsListener);
        mCommunityEventsReference.addValueEventListener(mCommunityEventsListener);
        mSocialEventsReference.addValueEventListener(mSocialEventsListener);

        data.add(mInformationalEvents);
        data.add(mCommunityEvents);
        data.add(mSocialEvents);

        adapter.setmData(data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

        return rootView;
    }



    private ValueEventListener getInformationEvents(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mInformationalEvents.invisibleChildren.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    RushEvent rushEvent = snapshot.getValue(RushEvent.class);
                    mInformationalEvents.invisibleChildren.add(new Item(RushEventAdapter.CHILD,rushEvent));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }


    private ValueEventListener getServiceEvents(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mCommunityEvents.invisibleChildren.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    RushEvent rushEvent = snapshot.getValue(RushEvent.class);
                    mCommunityEvents.invisibleChildren.add(new Item(RushEventAdapter.CHILD,rushEvent));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }


    private ValueEventListener getSocialEvents(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mSocialEvents.invisibleChildren.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    RushEvent rushEvent = snapshot.getValue(RushEvent.class);
                    mSocialEvents.invisibleChildren.add(new Item(RushEventAdapter.CHILD,rushEvent));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        if (mInformationalEventsListener!= null){
            mInformationalEventsReference.removeEventListener(mInformationalEventsListener);
        }

        if (mCommunityEventsListener !=null){
            mCommunityEventsReference.removeEventListener(mCommunityEventsListener);
        }

        if (mSocialEventsListener!=null){
            mSocialEventsReference.removeEventListener(mSocialEventsListener);
        }
    }

    @Override
    public void onRushEventClicked(RushEvent rushEvent) {
        if (rushEvent.isOnCampus()){
            Intent intent = CampusMapActivity.newIntent(getActivity(),rushEvent);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
        } else{
            Intent intent = MapActivity.newIntent(getActivity(),rushEvent);
            startActivity(intent);
            getActivity().overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);

        }
    }
}
