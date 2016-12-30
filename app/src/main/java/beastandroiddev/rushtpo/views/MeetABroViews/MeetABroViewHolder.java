package beastandroiddev.rushtpo.views.MeetABroViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.entities.Brother;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MeetABroViewHolder extends RecyclerView.ViewHolder{
    @BindView(R2.id.list_brothers_display_avatar)
    ImageView mBrotherPicture;



    public MeetABroViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void populate(BaseFragmentActivity activity, Brother brother, final View progressBar){
        itemView.setTag(brother);
        Picasso.with(activity)
                .load(brother.getBrotherPicture())
                .into(mBrotherPicture, new Callback() {
                    @Override
                    public void onSuccess() {
                      progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
