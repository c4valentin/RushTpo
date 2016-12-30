package beastandroiddev.rushtpo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.Brother;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BrotherDetailsFragment extends BaseFragment {
    private static final String BROTHER_EXTRA_INFO = "BROTHER_EXTRA_INFO";

    @BindView(R2.id.fragment_details_brothers_brotherImage)
    ImageView mBrotherImageView;

    @BindView(R2.id.fragment_details_brothers_brotherName)
    TextView mBrotherName;

    @BindView(R2.id.fragment_details_brothers_brotherMajor)
    TextView mBrotherMajor;

    @BindView(R2.id.fragment_details_brothers_funFact)
    TextView mBrotherFunFact;

    @BindView(R2.id.fragment_details_brothersCross)
    TextView mBrotherCrossSemester;

    @BindView(R2.id.fragment_details_brothers_ProgressBar)
    ProgressBar mProgressBar;

    @BindView(R2.id.fragment_details_brothers_whyJoin)
    TextView mBrotherWhyJoined;

    private Brother mBrother;
    private Unbinder mUnbinder;

    public static BrotherDetailsFragment newInstance(Brother brother){
        Bundle arguments = new Bundle();
        arguments.putParcelable(BROTHER_EXTRA_INFO,brother);
        BrotherDetailsFragment brotherDetailsFragment = new BrotherDetailsFragment();
        brotherDetailsFragment.setArguments(arguments);
        return brotherDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrother = getArguments().getParcelable(BROTHER_EXTRA_INFO);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brother_details,container,false);
        mUnbinder = ButterKnife.bind(this,rootView);
        mBrotherName.setText(mBrother.getBrotherName());
        mBrotherCrossSemester.setText(mBrother.getBrotherCrossSemester());
        mBrotherMajor.setText(mBrother.getBrotherMajor());
        mBrotherFunFact.setText(mBrother.getBrotherFunFact());
        mBrotherWhyJoined.setText(mBrother.getBrotherWhyJoined());

        Picasso.with(getActivity())
                .load(mBrother.getBrotherPicture())
                .into(mBrotherImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
