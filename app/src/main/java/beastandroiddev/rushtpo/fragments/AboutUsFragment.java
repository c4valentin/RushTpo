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
import beastandroiddev.rushtpo.activities.EventPhotoActivity;
import beastandroiddev.rushtpo.activities.YoutubePlayerActivity;
import beastandroiddev.rushtpo.entities.InformationCard;
import beastandroiddev.rushtpo.utils.Constants;
import beastandroiddev.rushtpo.views.AboutUsViews.AboutUsAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AboutUsFragment extends BaseFragment implements AboutUsAdapter.InformationCardListener {

    @BindView(R2.id.fragment_about_us_recyclerView)
    RecyclerView mRecyclerView;

    private AboutUsAdapter mAdapter;

    private DatabaseReference mServiceCardsReference;
    private ValueEventListener mServiceCardsListener;

    private DatabaseReference mSocialCardsReference;
    private ValueEventListener mSocialCardsListener;

    private DatabaseReference mBrotherHoodCardsReference;
    private ValueEventListener mBrotherHoodCardsListener;

    private Unbinder mUnbinder;

    public static AboutUsFragment newInstance(){
        return new AboutUsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_us,container,false);
        mUnbinder = ButterKnife.bind(this,rootView);
        mAdapter = new AboutUsAdapter((BaseFragmentActivity)getActivity(),this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mServiceCardsReference = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIRE_BASE_PATH_INFORMATION_CARDS)
                .child(Constants.FIRE_BASE_PATH_ASU)
                .child(Constants.FIRE_BASE_PATH_SERVICE_CARDS);

        mBrotherHoodCardsReference = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIRE_BASE_PATH_INFORMATION_CARDS)
                .child(Constants.FIRE_BASE_PATH_ASU)
                .child(Constants.FIRE_BASE_PATH_BROTHERHOOD_CARDS);

        mSocialCardsReference = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIRE_BASE_PATH_INFORMATION_CARDS)
                .child(Constants.FIRE_BASE_PATH_ASU)
                .child(Constants.FIRE_BASE_PATH_SOCIAL_CARDS);


        mServiceCardsListener = getInformationCards(1);
        mBrotherHoodCardsListener = getInformationCards(2);
        mSocialCardsListener = getInformationCards(3);

        mServiceCardsReference.addValueEventListener(mServiceCardsListener);
        mBrotherHoodCardsReference.addValueEventListener(mBrotherHoodCardsListener);
        mSocialCardsReference.addValueEventListener(mSocialCardsListener);

        mRecyclerView.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void OnInformationCardClicked(InformationCard informationCard) {
        if (informationCard.getCardTitle().equals("Philanthropy")){
            Intent intent = EventPhotoActivity.newIntent(getActivity(),EventPhotoActivity.EXTRA_INTEGER_SERVICE);
            startActivity(intent);
        } else if(informationCard.getCardTitle().equals("Traveling")){
            Intent intent = EventPhotoActivity.newIntent(getActivity(),EventPhotoActivity.EXTRA_INTEGER_TRAVELING);
            startActivity(intent);
        } else if (informationCard.getCardTitle().equals("Sexy Showcase")){
            Intent intent = EventPhotoActivity.newIntent(getActivity(),EventPhotoActivity.EXTRA_INTEGER_SEXY_SHOWCASE);
            startActivity(intent);
        } else if (informationCard.isVideo()){
            Intent intent = YoutubePlayerActivity.newIntent(getActivity(),informationCard.getUrl());
            startActivity(intent);
        }
    }


    private ValueEventListener getInformationCards(final int cardType){
        final List<InformationCard> informationCards = new ArrayList<>();
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                informationCards.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    InformationCard informationCard = snapshot.getValue(InformationCard.class);
                    informationCards.add(informationCard);
                }

                if (cardType == 1){
                    mAdapter.setmServiceCards(informationCards);
                } else if (cardType ==2){
                    mAdapter.setmBrotherHoodCards(informationCards);
                } else if (cardType ==3){
                    mAdapter.setmSocialCards(informationCards);
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

        if (mServiceCardsListener!=null){
            mServiceCardsReference.removeEventListener(mServiceCardsListener);
        }

        if (mBrotherHoodCardsListener!=null){
            mBrotherHoodCardsReference.removeEventListener(mBrotherHoodCardsListener);
        }

        if (mSocialCardsListener!=null){
            mSocialCardsReference.removeEventListener(mSocialCardsListener);
        }
    }
}
