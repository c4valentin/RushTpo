package beastandroiddev.rushtpo.views.MeetABroViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.entities.Brother;

public class MeetABroAdapter extends RecyclerView.Adapter {

    private BaseFragmentActivity mActivity;
    private List<Brother> mBrothers;
    private LayoutInflater mInflater;
    private BrotherClickedListener mListener;
    private View mProgressBar;

    public MeetABroAdapter(BaseFragmentActivity mActivity, BrotherClickedListener mListener, View mProgressBar) {
        this.mActivity = mActivity;
        this.mListener = mListener;
        this.mProgressBar = mProgressBar;
        mInflater = mActivity.getLayoutInflater();
        mBrothers = new ArrayList<>();
    }

    public void setmBrothers(List<Brother> brothers) {
        mBrothers.clear();
        mBrothers.addAll(brothers);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mInflater.inflate(R.layout.list_brothers_display,parent,false);
        final MeetABroViewHolder meetABroViewHolder = new MeetABroViewHolder(rootView);
        meetABroViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Brother brother = (Brother) meetABroViewHolder.itemView.getTag();
                mListener.OnBrotherClicked(brother);
            }
        });
        return meetABroViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MeetABroViewHolder) holder).populate(mActivity,mBrothers.get(position),mProgressBar);
    }

    @Override
    public int getItemCount() {
        return mBrothers.size();
    }

    public interface BrotherClickedListener{
        void OnBrotherClicked(Brother brother);
    }
}
