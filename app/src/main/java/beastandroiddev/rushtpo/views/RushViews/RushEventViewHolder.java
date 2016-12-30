package beastandroiddev.rushtpo.views.RushViews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.RushEvent;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RushEventViewHolder extends RecyclerView.ViewHolder {

    @BindView(R2.id.list_rush_events_name)
    TextView mRushEventName;

    @BindView(R2.id.list_rush_event_location)
    TextView mRushEventLocation;

    @BindView(R2.id.list_rush_events_date)
    TextView mRushEventDate;

    @BindView(R2.id.list_rush_event_time)
    TextView mRushEventTime;


    public RushEventViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void populate(RushEvent rushEvent){
        itemView.setTag(rushEvent);
        mRushEventName.setText(rushEvent.getRushEventTitle());
        mRushEventLocation.setText(rushEvent.getRushEventLocation());
        mRushEventDate.setText(rushEvent.getRushEventDate());
        mRushEventTime.setText(rushEvent.getRushEventTime());
    }
}
