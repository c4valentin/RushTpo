package beastandroiddev.rushtpo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import beastandroiddev.rushtpo.R;
import beastandroiddev.rushtpo.R2;
import beastandroiddev.rushtpo.entities.EventPictures;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EventPhotoFragment extends BaseFragment {
    @BindView(R2.id.fragment_event_photoView)
    ImageView mPhotoView;

    private Unbinder mUnbinder;

    private EventPictures mEventPictures;

    private final static String EVENT_PHOTO = "EVENT_PHOTO";


    public static EventPhotoFragment newInstance(EventPictures eventPictures){
        Bundle arguments = new Bundle();
        arguments.putParcelable(EVENT_PHOTO,eventPictures);
        EventPhotoFragment eventPhotoFragment = new EventPhotoFragment();
        eventPhotoFragment.setArguments(arguments);
        return eventPhotoFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventPictures = getArguments().getParcelable(EVENT_PHOTO);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_photo,container,false);
        mUnbinder = ButterKnife.bind(this,rootView);

        Picasso.with(getActivity())
                    .load(mEventPictures.getUrl())
                    .into(mPhotoView);



        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
