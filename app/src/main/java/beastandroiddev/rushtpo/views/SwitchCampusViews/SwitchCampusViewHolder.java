package beastandroiddev.rushtpo.views.SwitchCampusViews;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.Campus;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SwitchCampusViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.list_campus_layout_name)
    TextView mCampusName;

    @BindView(R2.id.list_campus_layout_check)
    ImageView mCheckMark;

    @BindView(R2.id.list_campus_layout)
    View mLayoutView;

    public SwitchCampusViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void populate(Context context,Campus campus,String currentCampusName){
        itemView.setTag(campus);
        mCampusName.setText(context.getString(R.string.campus_title,campus.getCampusName()));

        if (currentCampusName.equals(campus.getCampusName())){
            mCheckMark.setVisibility(View.VISIBLE);
            mCampusName.setTextColor(ContextCompat.getColor(context,R.color.black));
            mLayoutView.setBackgroundColor(ContextCompat.getColor(context,R.color.white));

        } else{
            mCheckMark.setVisibility(View.GONE);

            mCampusName.setTextColor(ContextCompat.getColor(context,R.color.silver_accent));
            mLayoutView.setBackgroundColor(ContextCompat.getColor(context,R.color.white));

        }
    }
}
