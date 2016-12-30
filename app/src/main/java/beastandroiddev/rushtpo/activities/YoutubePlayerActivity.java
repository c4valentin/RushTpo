package beastandroiddev.rushtpo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import beastandroiddev.rushtpo.R;

public class YoutubePlayerActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener{

    private String videoUrl;
    public static final String EXTRA_VIDEO_INFO = "EXTRA_VIDEO_INFO";



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.screenBrightness = 1F;
        setContentView(R.layout.activity_youtube_player);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(layoutParams);
        videoUrl = getIntent().getStringExtra(EXTRA_VIDEO_INFO);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.fragment_youtube_player_video);
        youTubePlayerView.initialize(getString(R.string.YouTubeKey), this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(videoUrl);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    public static Intent newIntent(Context context,String videoString){
        Intent intent = new Intent(context,YoutubePlayerActivity.class);
        intent.putExtra(EXTRA_VIDEO_INFO,videoString);
        return intent;
    }
}
