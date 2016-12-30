package beastandroiddev.rushtpo.views.RushViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.entities.RushEvent;

public class RushEventAdapter extends RecyclerView.Adapter {

    private static final int LIST_HEADER =1;
    public static final int HEADER = 2;
    public static final int CHILD = 3;
    private static final int LIST_FOOTER = 4;


    private List<Item> mData;
    private BaseFragmentActivity mActivity;
    private LayoutInflater mInflater;
    private RushEventListener mListener;
    private String mUserCampus;


    public RushEventAdapter(BaseFragmentActivity mActivity, RushEventListener mListener, String mUserCampus) {
        this.mActivity = mActivity;
        this.mListener = mListener;
        this.mUserCampus = mUserCampus;
        mInflater = mActivity.getLayoutInflater();
        mData = new ArrayList<>();
    }


    public void setmData(List<Item> data) {
        mData.addAll(data);
    }

    @Override
    public int getItemViewType(int position) {
        if(position ==0){
            return LIST_HEADER;
        }

        position --;


        if(position<mData.size()){
            return mData.get(position).type;
        }


        position -= mData.size();


        if(position<mData.size()){
            return LIST_FOOTER;
        }

        position --;


        throw new IllegalArgumentException(
                "we are being asked for an item type for position "+ position +", though we have no such item"
        );
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headerView = mInflater.inflate(R.layout.header_rush,parent,false);
        View expandableHeaderView = mInflater.inflate(R.layout.list_rush_expandable_header,parent,false);
        View rushEventView = mInflater.inflate(R.layout.list_rush_events,parent,false);
        View footerView = mInflater.inflate(R.layout.footer_rush_social_media,parent,false);

        switch (viewType){
            case LIST_HEADER:
                return new RushHeaderHolder(headerView);


            case HEADER:
                return new RushExpandableHeaderHolder(expandableHeaderView);


            case CHILD:
                final RushEventViewHolder rushEventViewHolder = new RushEventViewHolder(rushEventView);
                rushEventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RushEvent rushEvent = (RushEvent) rushEventViewHolder.itemView.getTag();
                        mListener.onRushEventClicked(rushEvent);
                    }
                });

                return rushEventViewHolder;

            case LIST_FOOTER:
                return new RushFooterHolder(footerView,mUserCampus,mActivity);
        }

        throw new IllegalArgumentException("ViewType " + viewType + " is not supported");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RushExpandableHeaderHolder){
            position --;
            final Item item = mData.get(position);

            final RushExpandableHeaderHolder viewHolder = (RushExpandableHeaderHolder) holder;
            viewHolder.mReferalItem = item;
            viewHolder.mHeaderTitle.setText(item.text);

            if (item.invisibleChildren ==null){
                viewHolder.mButtonToggle.setImageResource(R.mipmap.ic_close);
            } else {
                viewHolder.mButtonToggle.setImageResource(R.mipmap.ic_plus);
            }

            viewHolder.mBackgoundView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (item.invisibleChildren ==null){
                        item.invisibleChildren = new ArrayList<>();
                        int count =0;
                        int position = mData.indexOf(viewHolder.mReferalItem);

                        while(mData.size()>position+1 && mData.get(position+1).type ==CHILD){
                            item.invisibleChildren.add(mData.remove(position+1));
                            count ++;
                        }
                        notifyItemRangeRemoved(position+1,count);
                    } else{
                        int position = mData.indexOf(viewHolder.mReferalItem);
                        int index = position +1;
                        for (Item item1: item.invisibleChildren){
                            mData.add(index,item1);
                            index ++;
                        }
                        notifyItemRangeInserted(position +1, index - position -1);
                        item.invisibleChildren = null;
                    }

                }
            });
        } else if(holder instanceof RushEventViewHolder){
            position --;
            RushEventViewHolder rushEventViewHolder = (RushEventViewHolder) holder;
            rushEventViewHolder.populate(mData.get(position).rushEvent);
        } else if(holder instanceof RushFooterHolder){
            ((RushFooterHolder) holder).populate(mActivity);
        }
    }

    @Override
    public int getItemCount() {
        int count;
        if (!mData.isEmpty()){
             count = 2 + mData.size();
        } else {
             count =2;
        }

        return count;
    }

    public interface RushEventListener {
        void onRushEventClicked(RushEvent rushEvent);
    }
}
