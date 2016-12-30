package beastandroiddev.rushtpo.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.RushEvent;
import beastandroiddev.rushtpo.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CampusMapActivity extends AppCompatActivity {

    @BindView(R2.id.activity_campus_map_rushName)
    TextView mRushEventName;

    @BindView(R2.id.activity_campus_map_rushDescription)
    TextView mRushDescription;

    @BindView(R2.id.activity_campus_map_date)
    TextView mRushDate;

    @BindView(R2.id.activity_campus_map_progressBar)
    View mProgressBar;

    @BindView(R2.id.activity_campus_map_rushLocation)
    TextView mRushEventLocation;

    @BindView(R2.id.activity_campus_map_webView)
    WebView mWebview;

    @BindView(R2.id.activity_campus_map_time)
    TextView mRushEventTime;


    private final static String RUSH_EVENT_INFO = "RUSH_EVENT_INFO";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        ButterKnife.bind(this);
        RushEvent rushEvent = getIntent().getParcelableExtra(RUSH_EVENT_INFO);
        mRushEventName.setText(rushEvent.getRushEventTitle());
        mRushEventLocation.setText(rushEvent.getRushEventLocation());
        mRushDate.setText(rushEvent.getRushEventDate());
        mRushEventTime.setText(rushEvent.getRushEventTime());
        mRushDescription.setText(rushEvent.getRushEventDescription());

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_SHARED_PREFERANCE,
                Context.MODE_PRIVATE);
        String link;


        String googleDocs = "http://docs.google.com/gview?embedded=true&url=";
        if (sharedPreferences.getString(Constants.USER_CAMPUS,"").equals("Arizona State University")){
            if (rushEvent.isOnMainCampus()){
                link = "http://www.asu.edu/map/pdf/asu_map_tempe_2016.pdf";
            } else{
                link = "https://www.asu.edu/map/pdf/asu_map_dwntwnphx_2016.pdf";
            }
        } else{
            link = "http://www.colorado.edu/campusmap/map.pdf";
        }



        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.getSettings().setSupportZoom(true);

        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });
        mWebview.loadUrl(googleDocs + link);
    }

    public static Intent newIntent(Context context,RushEvent rushEvent){
        Intent intent = new Intent(context,CampusMapActivity.class);
        intent.putExtra(RUSH_EVENT_INFO,rushEvent);
        return intent;
    }
}
