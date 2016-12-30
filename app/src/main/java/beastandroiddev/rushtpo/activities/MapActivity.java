package beastandroiddev.rushtpo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.RushEvent;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MapActivity extends AppCompatActivity {

    @BindView(R2.id.activity_map_rushName)
    TextView mRushEventName;

    @BindView(R2.id.activity_maps_rushDate)
    TextView mRushEventDate;

    @BindView(R2.id.activity_maps_rushLocation)
    TextView mRushEventLocation;

    @BindView(R2.id.activity_maps_rushDescription)
    TextView mRushEventDescription;

    @BindView(R2.id.activity_maps_rushTime)
    TextView mRushEventTime;

    @BindView(R2.id.activity_maps_progressBar)
    View mProgressBar;


    private GoogleApiClient mClient;
    private GoogleMap mMap;

    private RushEvent mRushEvent;
    private final static String RUSH_EVENT_INFO = "RUSH_EVENT_INFO";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
        mRushEvent = getIntent().getParcelableExtra(RUSH_EVENT_INFO);
        mRushEventName.setText(mRushEvent.getRushEventTitle());
        mRushEventDate.setText(mRushEvent.getRushEventDate());
        mRushEventDescription.setText(mRushEvent.getRushEventDescription());
        mRushEventLocation.setText(mRushEvent.getRushEventLocation());
        mRushEventTime.setText(mRushEvent.getRushEventTime());

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        upDateUI();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                }).build();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_map_maps);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mProgressBar.setVisibility(View.GONE);
                mMap = googleMap;
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mClient.disconnect();
    }


    private void upDateUI(){
        LatLng rushEventPoint = new LatLng(
                mRushEvent.getRushEventLatitude(),mRushEvent.getRushEventLongitude()
        );

        MarkerOptions rushEventMarker = new MarkerOptions()
                .position(rushEventPoint)
                .title("Rush Event Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        mMap.clear();
        mMap.addMarker(rushEventMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rushEventPoint,15));
    }

    public static Intent newIntent(Context context, RushEvent rushEvent){
        Intent intent = new Intent(context,MapActivity.class);
        intent.putExtra(RUSH_EVENT_INFO,rushEvent);
        return intent;
    }
}
