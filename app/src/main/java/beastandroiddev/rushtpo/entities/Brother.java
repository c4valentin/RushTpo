package beastandroiddev.rushtpo.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Brother implements Parcelable {
    private String brotherId;
    private String brotherCrossSemester;
    private String brotherFunFact;
    private String brotherMajor;
    private String brotherName;
    private String brotherPicture;
    private String brotherWhyJoined;

    public Brother(String brotherId, String brotherCrossSemester, String brotherFunFact, String brotherMajor, String brotherName, String brotherPicture, String brotherWhyJoined) {
        this.brotherId = brotherId;
        this.brotherCrossSemester = brotherCrossSemester;
        this.brotherFunFact = brotherFunFact;
        this.brotherMajor = brotherMajor;
        this.brotherName = brotherName;
        this.brotherPicture = brotherPicture;
        this.brotherWhyJoined = brotherWhyJoined;
    }

    public Brother() {
    }

    protected Brother(Parcel in) {
        brotherId = in.readString();
        brotherCrossSemester = in.readString();
        brotherFunFact = in.readString();
        brotherMajor = in.readString();
        brotherName = in.readString();
        brotherPicture = in.readString();
        brotherWhyJoined = in.readString();
    }

    public static final Creator<Brother> CREATOR = new Creator<Brother>() {
        @Override
        public Brother createFromParcel(Parcel in) {
            return new Brother(in);
        }

        @Override
        public Brother[] newArray(int size) {
            return new Brother[size];
        }
    };

    public String getBrotherId() {
        return brotherId;
    }

    public String getBrotherCrossSemester() {
        return brotherCrossSemester;
    }

    public String getBrotherFunFact() {
        return brotherFunFact;
    }

    public String getBrotherMajor() {
        return brotherMajor;
    }

    public String getBrotherName() {
        return brotherName;
    }

    public String getBrotherPicture() {
        return brotherPicture;
    }

    public String getBrotherWhyJoined() {
        return brotherWhyJoined;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brotherId);
        dest.writeString(brotherCrossSemester);
        dest.writeString(brotherFunFact);
        dest.writeString(brotherMajor);
        dest.writeString(brotherName);
        dest.writeString(brotherPicture);
        dest.writeString(brotherWhyJoined);
    }
}
