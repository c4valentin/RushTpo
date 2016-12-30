package beastandroiddev.rushtpo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.EventPictures;
import beastandroiddev.rushtpo.fragments.EventPhotoFragment;
import beastandroiddev.rushtpo.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventPhotoActivity extends AppCompatActivity {
    private ArrayList<EventPictures> mEventPhotos;

    @BindView(R2.id.activity_photo_event_viewPager)
    ViewPager mViewPager;

    public static final String EXTRA_REQUEST_INTEGER = "EXTRA_VIDEO_INFO";
    public static final int EXTRA_INTEGER_SERVICE = 1;
    public static final int EXTRA_INTEGER_TRAVELING = 2;
    public static final int EXTRA_INTEGER_SEXY_SHOWCASE = 3;

    private DatabaseReference mPictureReference;
    private ValueEventListener mPictureEventListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_photo);
        ButterKnife.bind(this);
        mEventPhotos = new ArrayList<>();
        int requestInt = getIntent().getIntExtra(EXTRA_REQUEST_INTEGER,1);
        DatabaseReference pictureDatabase = FirebaseDatabase.getInstance().getReference()
                .child(Constants.FIRE_BASE_PATH_EVENT_PICTURES).child(Constants.FIRE_BASE_PATH_ASU);

        switch (requestInt){
            case EXTRA_INTEGER_SERVICE:
                Log.i(EventPhotoActivity.class.getSimpleName(),"service was called");
                mPictureReference = pictureDatabase.child(Constants.FIRE_BASE_PATH_SERVICE_PICTURES);
                break;
            case EXTRA_INTEGER_TRAVELING:
                Log.i(EventPhotoActivity.class.getSimpleName(),"traveling was called");
                mPictureReference = pictureDatabase.child(Constants.FIRE_BASE_PATH_TRAVELING_PICTURES);
                break;

            case EXTRA_INTEGER_SEXY_SHOWCASE:
                Log.i(EventPhotoActivity.class.getSimpleName(),"sexyShowcase was called");
                mPictureReference = pictureDatabase.child(Constants.FIRE_BASE_PATH_SEXY_SHOWCASE_PICTURES);
                break;
        }

        mPictureEventListener = getEventPictures();
        mPictureReference.addValueEventListener(mPictureEventListener);


        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                EventPictures eventPictures = mEventPhotos.get(position);
                return EventPhotoFragment.newInstance(eventPictures);
            }

            @Override
            public int getCount() {
                return mEventPhotos.size();
            }
        });
    }



    private ValueEventListener getEventPictures(){
        return new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    mEventPhotos.add(snapshot.getValue(EventPictures.class));
                }
                mViewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    public static Intent newIntent(Context context, int requestPicturesInt){
        Intent intent = new Intent(context,EventPhotoActivity.class);
        intent.putExtra(EXTRA_REQUEST_INTEGER,requestPicturesInt);
        return intent;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPictureEventListener !=null){
            mPictureReference.removeEventListener(mPictureEventListener);
        }
    }
}
