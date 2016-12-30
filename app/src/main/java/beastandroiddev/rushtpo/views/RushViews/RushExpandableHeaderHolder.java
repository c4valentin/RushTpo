package beastandroiddev.rushtpo.views.RushViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import beastandroiddev.rushtpo.R2;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RushExpandableHeaderHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.list_rush_expandable_header_background)
    View mBackgoundView;

    @BindView(R2.id.list_rush_expandable_header_title)
    TextView mHeaderTitle;

    @BindView(R2.id.list_rush_expandable_header_button)
    ImageView mButtonToggle;

    public Item mReferalItem;

    public RushExpandableHeaderHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
