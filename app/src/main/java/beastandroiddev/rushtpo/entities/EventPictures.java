package beastandroiddev.rushtpo.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class EventPictures implements Parcelable {
    private String url;

    public EventPictures() {
    }

    public EventPictures(String url) {
        this.url = url;
    }

    protected EventPictures(Parcel in) {
        url = in.readString();
    }

    public static final Creator<EventPictures> CREATOR = new Creator<EventPictures>() {
        @Override
        public EventPictures createFromParcel(Parcel in) {
            return new EventPictures(in);
        }

        @Override
        public EventPictures[] newArray(int size) {
            return new EventPictures[size];
        }
    };

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
