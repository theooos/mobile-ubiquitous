package red.theodo.restauranthygienechecker1453831;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by theo on 06/03/18.
 */

public class Establishment implements Parcelable {

    private String name;
    private String businessType;
    private String address;
    private String localAuthorityName;
    private String localAuthorityEmailAddress;

    private String rating;
    private long ratingDate;

    private float longitude;
    private float latitude;
    private float distance;

    public Establishment(String name, String businessType, String address, String localAuthorityName, String localAuthorityEmailAddress, String rating, long ratingDate, float longitude, float latitude, float distance) {
        this.name = name;
        this.businessType = businessType;
        this.address = address;
        this.localAuthorityName = localAuthorityName;
        this.localAuthorityEmailAddress = localAuthorityEmailAddress;
        this.rating = rating;
        this.ratingDate = ratingDate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.distance = distance;
    }

    public String toString(){
        return name;
    }


    // **** PARCELABLE IMPLEMENTATION ****

    protected Establishment(Parcel in) {
        name = in.readString();
        businessType = in.readString();
        address = in.readString();
        localAuthorityName = in.readString();
        localAuthorityEmailAddress = in.readString();
        rating = in.readString();
        ratingDate = in.readLong();
        longitude = in.readFloat();
        latitude = in.readFloat();
        distance = in.readFloat();
    }

    public static final Creator<Establishment> CREATOR = new Creator<Establishment>() {
        @Override
        public Establishment createFromParcel(Parcel in) {
            return new Establishment(in);
        }

        @Override
        public Establishment[] newArray(int size) {
            return new Establishment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(businessType);
        parcel.writeString(address);
        parcel.writeString(localAuthorityName);
        parcel.writeString(localAuthorityEmailAddress);
        parcel.writeString(rating);
        parcel.writeLong(ratingDate);
        parcel.writeFloat(longitude);
        parcel.writeFloat(latitude);
        parcel.writeFloat(distance);
    }
}
