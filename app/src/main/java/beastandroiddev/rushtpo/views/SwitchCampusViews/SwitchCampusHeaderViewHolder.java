package beastandroiddev.rushtpo.views.SwitchCampusViews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import beastandroiddev.rushtpo.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SwitchCampusHeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R2.id.header_campus_imageView)
    ImageView campusPicture;

    public SwitchCampusHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void populate(Context context, String campusPictureUrl){
        Picasso.with(context).load(campusPictureUrl)
                .into(campusPicture);
    }
}
