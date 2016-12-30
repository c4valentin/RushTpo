package beastandroiddev.rushtpo.views.AboutUsViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.InformationCard;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.list_information_cards_eventName)
    TextView mInformationCardName;

    @BindView(R2.id.list_information_cards_display_eventDescription)
    TextView mInformationCardDescription;

    @BindView(R2.id.list_information_cards_display_eventPicture)
    ImageView mInformationCardPicture;

    @BindView(R2.id.list_information_video_icon)
    ImageView mInformationCardMediaIcon;

    @BindView(R2.id.list_information_cards_display_progressBar)
    ProgressBar mInformationCardProgressBar;



    public ServiceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void populate(Context context, InformationCard informationCards){
        itemView.setTag(informationCards);

        mInformationCardName.setText(informationCards.getCardTitle());
        mInformationCardDescription.setText(informationCards.getCardDescription());

        Picasso.with(context)
                .load(informationCards.getCardPicture())
                .into(mInformationCardPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                        mInformationCardProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        if (!informationCards.isVideo()){
            mInformationCardMediaIcon.setImageResource(R.mipmap.ic_cam);
        } else{
            mInformationCardMediaIcon.setImageResource(R.mipmap.ic_vido);
        }

    }

}
