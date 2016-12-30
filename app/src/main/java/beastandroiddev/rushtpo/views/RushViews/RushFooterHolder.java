package beastandroiddev.rushtpo.views.RushViews;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.activities.YoutubePlayerActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RushFooterHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.footer_fragment_rush_facebookFollow)
    ImageView mFaceBookImage;

    @BindView(R2.id.footer_fragment_rush_InstagramFollow)
    ImageView mInstagramImage;

    @BindView(R2.id.footer_fragment_rush_twitterFollow)
    ImageView mTwitterImage;

    @BindView(R2.id.footer_fragment_rush_snapFollow)
    ImageView mSnapChatImage;

    @BindView(R2.id.footer_fragment_rush_rushVideo)
    ImageView mRushVideo;

    @BindView(R2.id.footer_fragment_rush_social_media_peName)
    TextView mPeName;

    @BindView(R2.id.footer_rush_social_media_peEmail)
    TextView mPeEmail;

    private BaseFragmentActivity mBaseFragmentActivity;

    public RushFooterHolder(View itemView,String currentCampus,BaseFragmentActivity baseFragmentActivity) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mBaseFragmentActivity = baseFragmentActivity;

        if (currentCampus.equals("Arizona State University")){
            setUpAsuCampus();
        } else{
            setUpCu();
        }
    }

    private void setUpAsuCampus(){
        mPeEmail.setText(mBaseFragmentActivity.getString(R.string.asu_pe_email));
        mPeName.setText(mBaseFragmentActivity.getString(R.string.asu_pe_name));

        mFaceBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    mBaseFragmentActivity.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/878570282249118"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TauPsiOmega.Alpha/"));
                }
                mBaseFragmentActivity.startActivity(intent);
            }
        });

        mSnapChatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nativeAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + "taupsiomega1996"));
                mBaseFragmentActivity.startActivity(nativeAppIntent);
            }
        });

        mTwitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    mBaseFragmentActivity.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=760490736554876929"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ASUTauPsi"));
                }
                mBaseFragmentActivity.startActivity(intent);

            }
        });

        mInstagramImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                try {
                    mBaseFragmentActivity.getPackageManager().getPackageInfo("com.instagram.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/taupsiomega.alpha"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/taupsiomega.alpha/"));
                }

                mBaseFragmentActivity.startActivity(intent);
            }
        });

        mRushVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = YoutubePlayerActivity.newIntent(mBaseFragmentActivity,"xX8SmyfV8S8");
                mBaseFragmentActivity.startActivity(intent);
            }
        });
    }


    private void setUpCu(){
        mPeEmail.setText(mBaseFragmentActivity.getString(R.string.cu_pe_email));
        mPeName.setText(mBaseFragmentActivity.getString(R.string.cu_pe_name));

        mFaceBookImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    mBaseFragmentActivity.getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/1713833655558388"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/TPOBoulder"));
                }
                mBaseFragmentActivity.startActivity(intent);
            }
        });

        mSnapChatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nativeAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://snapchat.com/add/" + "taupsiomega1996"));
                mBaseFragmentActivity.startActivity(nativeAppIntent);
            }
        });

        mTwitterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                try {
                    mBaseFragmentActivity.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=52146950"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/TauPsiOmega"));
                }
                mBaseFragmentActivity.startActivity(intent);

            }
        });

        mInstagramImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent;
                try {
                    mBaseFragmentActivity.getPackageManager().getPackageInfo("com.instagram.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/_u/Taupsiomega.beta"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/Taupsiomega.beta/"));
                }

                mBaseFragmentActivity.startActivity(intent);
            }
        });

        mRushVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = YoutubePlayerActivity.newIntent(mBaseFragmentActivity,"xX8SmyfV8S8");
                mBaseFragmentActivity.startActivity(intent);
            }
        });
    }

    public void populate(Context context){
        Picasso.with(context).load("https://dl.dropboxusercontent.com/s/pq8wwspwtvdtg0c/facebook.jpg?dl=0").into(mFaceBookImage);
        Picasso.with(context).load("https://dl.dropboxusercontent.com/s/6cxxu6krjl4v9bk/instagram.jpg?dl=0").into(mInstagramImage);
        Picasso.with(context).load("https://dl.dropboxusercontent.com/s/zjgpxm4l6di2gfh/twitter.JPG?dl=0").into(mTwitterImage);
        Picasso.with(context).load("https://dl.dropboxusercontent.com/s/u2r0ax4brp27t4e/snapChat.jpg?dl=0").into(mSnapChatImage);
    }
}
