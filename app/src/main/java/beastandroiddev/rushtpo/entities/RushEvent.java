package beastandroiddev.rushtpo.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class RushEvent implements Parcelable{
    private String rushEventTitle;
    private String rushEventDescription;
    private String rushEventTime;
    private String rushEventDate;
    private String rushEventLocation;
    private double rushEventLatitude;
    private double rushEventLongitude;
    private boolean onCampus;
    private boolean onMainCampus;

    public RushEvent(String rushEventTitle, String rushEventDescription, String rushEventTime, String rushEventDate, String rushEventLocation, double rushEventlatitude, double rushEventlongitude, boolean onCampus, boolean onMainCampus) {
        this.rushEventTitle = rushEventTitle;
        this.rushEventDescription = rushEventDescription;
        this.rushEventTime = rushEventTime;
        this.rushEventDate = rushEventDate;
        this.rushEventLocation = rushEventLocation;
        this.rushEventLatitude = rushEventlatitude;
        this.rushEventLongitude = rushEventlongitude;
        this.onCampus = onCampus;
        this.onMainCampus = onMainCampus;
    }

    public RushEvent() {
    }


    protected RushEvent(Parcel in) {
        rushEventTitle = in.readString();
        rushEventDescription = in.readString();
        rushEventTime = in.readString();
        rushEventDate = in.readString();
        rushEventLocation = in.readString();
        rushEventLatitude = in.readDouble();
        rushEventLongitude = in.readDouble();
        onCampus = in.readByte() != 0;
        onMainCampus = in.readByte() != 0;
    }

    public static final Creator<RushEvent> CREATOR = new Creator<RushEvent>() {
        @Override
        public RushEvent createFromParcel(Parcel in) {
            return new RushEvent(in);
        }

        @Override
        public RushEvent[] newArray(int size) {
            return new RushEvent[size];
        }
    };

    public String getRushEventTitle() {
        return rushEventTitle;
    }

    public String getRushEventDescription() {
        return rushEventDescription;
    }

    public String getRushEventTime() {
        return rushEventTime;
    }

    public String getRushEventDate() {
        return rushEventDate;
    }

    public String getRushEventLocation() {
        return rushEventLocation;
    }

    public double getRushEventLatitude() {
        return rushEventLatitude;
    }

    public double getRushEventLongitude() {
        return rushEventLongitude;
    }

    public boolean isOnCampus() {
        return onCampus;
    }

    public boolean isOnMainCampus() {
        return onMainCampus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(rushEventTitle);
        dest.writeString(rushEventDescription);
        dest.writeString(rushEventTime);
        dest.writeString(rushEventDate);
        dest.writeString(rushEventLocation);
        dest.writeDouble(rushEventLatitude);
        dest.writeDouble(rushEventLongitude);
        dest.writeByte((byte) (onCampus ? 1 : 0));
        dest.writeByte((byte) (onMainCampus ? 1 : 0));
    }
}
