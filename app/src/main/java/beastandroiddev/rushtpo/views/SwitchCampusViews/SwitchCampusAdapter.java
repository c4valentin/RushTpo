package beastandroiddev.rushtpo.views.SwitchCampusViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.entities.Campus;

public class SwitchCampusAdapter extends RecyclerView.Adapter {

    private final int VIEW_ID_HEADER =1;
    private final int VIEW_ID_BODY = 2;

    private BaseFragmentActivity mActivity;
    private CampusListener mListener;
    private List<Campus> mCampuses;
    private LayoutInflater mInflater;
    private String mCurrentCampus;
    private String mCurrentCampusUrl;


    public SwitchCampusAdapter(BaseFragmentActivity mActivity, CampusListener mListener, String mCurrentCampus, String mCurrentCampusUrl) {
        this.mActivity = mActivity;
        this.mListener = mListener;
        this.mCurrentCampus = mCurrentCampus;
        this.mCurrentCampusUrl = mCurrentCampusUrl;
        mInflater = mActivity.getLayoutInflater();
        mCampuses = new ArrayList<>();
    }

    public void setmCampuses(List<Campus> campuses) {
        mCampuses.clear();
        mCampuses.addAll(campuses);
        notifyDataSetChanged();
    }

    public List<Campus> getmCampuses() {
        return mCampuses;
    }

    @Override
    public int getItemViewType(int position) {
        if (position ==0){
            return VIEW_ID_HEADER;
        }

        position --;

        if (position<mCampuses.size()){
            return VIEW_ID_BODY;
        }

        position -= mCampuses.size();
        throw new IllegalArgumentException("We are at position " + position + " and cannot add another view type." );
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headerView = mInflater.inflate(R.layout.header_campus,parent,false);
        View bodyView = mInflater.inflate(R.layout.list_campus_display,parent,false);


        switch (viewType){
            case VIEW_ID_HEADER:
                return new SwitchCampusHeaderViewHolder(headerView);

            case VIEW_ID_BODY:
                final SwitchCampusViewHolder switchCampusViewHolder = new SwitchCampusViewHolder(bodyView);
                switchCampusViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Campus campus = (Campus) switchCampusViewHolder.itemView.getTag();
                        mListener.onCampusClicked(campus);
                    }
                });
                return switchCampusViewHolder;

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SwitchCampusHeaderViewHolder){
            ((SwitchCampusHeaderViewHolder) holder).populate(mActivity,mCurrentCampusUrl);
        } else if(holder instanceof SwitchCampusViewHolder){
            position --;
            ((SwitchCampusViewHolder) holder).populate(mActivity,mCampuses.get(position),
                    mCurrentCampus);
        }
    }

    @Override
    public int getItemCount() {
        return mCampuses.size() +1;
    }

    public interface CampusListener{
        void onCampusClicked(Campus campus);
    }
}
