package beastandroiddev.rushtpo.views.AboutUsViews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.activities.BaseFragmentActivity;
import beastandroiddev.rushtpo.entities.InformationCard;

public class AboutUsAdapter extends RecyclerView.Adapter {

    private final int HEADER_VIEW = 1;
    private final int HEADER_TITLE_VIEW =2;
    private final int BODY_SERVICE_VIEW =3;
    private final int BODY_BROTHERHOOD_VIEW = 4;
    private final int BODY_SOCIAL_VIEW =5;


    private List<InformationCard> mServiceCards;
    private List<InformationCard> mBrotherHoodCards;
    private List<InformationCard> mSocialCards;


    private BaseFragmentActivity mActivity;
    private LayoutInflater mInflater;
    private InformationCardListener mListener;

    public AboutUsAdapter(BaseFragmentActivity mActivity, InformationCardListener mListener) {
        this.mActivity = mActivity;
        this.mListener = mListener;
        mInflater = mActivity.getLayoutInflater();

        mServiceCards = new ArrayList<>();
        mBrotherHoodCards = new ArrayList<>();
        mSocialCards = new ArrayList<>();
    }

    public void setmServiceCards(List<InformationCard> serviceCards) {
        mServiceCards.clear();
        mServiceCards.addAll(serviceCards);
        notifyDataSetChanged();
    }

    public void setmBrotherHoodCards(List<InformationCard> brotherHoodCards) {
        mBrotherHoodCards.clear();
        mBrotherHoodCards.addAll(brotherHoodCards);
        notifyDataSetChanged();
    }

    public void setmSocialCards(List<InformationCard> socialCards) {
        mSocialCards.clear();
        mSocialCards.addAll(socialCards);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (position ==0){
            return HEADER_VIEW;
        }

        position --;



        if (mServiceCards.size()>0){
            if (position ==0){
                return HEADER_TITLE_VIEW;
            }


            position --;


            if (position<mServiceCards.size()){
                return BODY_SERVICE_VIEW;
            }

            position -= mServiceCards.size();
        }



        if (mBrotherHoodCards.size()>0){
            if (position ==0){
                return HEADER_TITLE_VIEW;
            }

            position --;
            if (position<mBrotherHoodCards.size()){
                return BODY_BROTHERHOOD_VIEW;
            }

            position -= mBrotherHoodCards.size();
        }


        if (mSocialCards.size()>0){
            if (position ==0){
                return HEADER_TITLE_VIEW;
            }
            position --;

            if (position<mSocialCards.size()){
                return BODY_SOCIAL_VIEW;
            }
            position -= mSocialCards.size();
        }

        throw new IllegalArgumentException("We are being asked fo a viewType at position " + position + " although we are at the " +
                "end of our list");
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View headerView = mInflater.inflate(R.layout.header_about_us,parent,false);
        View bodyView = mInflater.inflate(R.layout.list_information_cards_display,parent,false);
        View titleView = mInflater.inflate(R.layout.simple_header,parent,false);

        switch (viewType){
            case HEADER_VIEW:
                return new AboutUsHeaderViewHolder(headerView);

            case HEADER_TITLE_VIEW:
                return new AboutUsSimpleHeader(titleView);

            case BODY_SERVICE_VIEW:
                final ServiceViewHolder serviceViewHolder =
                        new ServiceViewHolder(bodyView);
                serviceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InformationCard informationCard = (InformationCard) serviceViewHolder.itemView.getTag();
                        mListener.OnInformationCardClicked(informationCard);
                    }
                });

                return serviceViewHolder;



            case BODY_BROTHERHOOD_VIEW:
                final BrotherHoodViewHolder brotherHoodViewHolder =
                        new BrotherHoodViewHolder(bodyView);
                brotherHoodViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InformationCard informationCard = (InformationCard) brotherHoodViewHolder.itemView.getTag();
                        mListener.OnInformationCardClicked(informationCard);
                    }
                });
                return brotherHoodViewHolder;



            case BODY_SOCIAL_VIEW:
                final SocialViewHolder socialViewHolder =
                        new SocialViewHolder(bodyView);
                socialViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InformationCard informationCard = (InformationCard) socialViewHolder.itemView.getTag();
                        mListener.OnInformationCardClicked(informationCard);
                    }
                });
                return socialViewHolder;

            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ServiceViewHolder){
            position --;
            if (mServiceCards.size()>0){
                position --;
            }
            ((ServiceViewHolder) holder).populate(mActivity,mServiceCards.get(position));
        }

        if (holder instanceof BrotherHoodViewHolder){
            position --;
            if (mServiceCards.size()>0){
                position --;
                position -= mServiceCards.size();
            }

            if (mBrotherHoodCards.size()>0){
                position --;
            }
            ((BrotherHoodViewHolder) holder).populate(mActivity,mBrotherHoodCards.get(position));
        }

        if (holder instanceof SocialViewHolder){
            position --;
            if (mServiceCards.size()>0){
                position --;
                position -= mServiceCards.size();
            }

            if (mBrotherHoodCards.size()>0){
                position --;
                position -= mBrotherHoodCards.size();
            }

            if (mSocialCards.size()>0){
                position --;
            }
            ((SocialViewHolder) holder).populate(mActivity,mSocialCards.get(position));
        }

        if (holder instanceof AboutUsSimpleHeader){
            AboutUsSimpleHeader aboutUsSimpleHeader = (AboutUsSimpleHeader) holder;

            int servicePosition = 1;
            int brotherHoodPosition = servicePosition + mServiceCards.size() +1;
            int socialPosition = brotherHoodPosition + mBrotherHoodCards.size() +1;


            if(position == servicePosition){
                aboutUsSimpleHeader.populate("Community Service Events");
            }

            if (position == brotherHoodPosition){
                aboutUsSimpleHeader.populate("BrotherHood Events");
            }

            if (position == socialPosition){
                aboutUsSimpleHeader.populate("Social Events");
            }
        }
    }

    @Override
    public int getItemCount() {
        int count = 1;

        if (mServiceCards.size()>0){
            count += 1+mServiceCards.size();
        }

        if (mBrotherHoodCards.size()>0){
            count += 1+mBrotherHoodCards.size();
        }

        if (mSocialCards.size()>0){
            count += 1+mSocialCards.size();
        }

        return count;
    }

    public interface InformationCardListener{
        void OnInformationCardClicked(InformationCard informationCard);
    }
}
