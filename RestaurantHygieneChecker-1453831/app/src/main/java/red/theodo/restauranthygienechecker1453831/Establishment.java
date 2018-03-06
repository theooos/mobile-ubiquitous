package red.theodo.restauranthygienechecker1453831;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by theo on 06/03/18.
 */

public class Establishment implements Parcelable {
    private String name;
    private String rating;

    public Establishment(String name, String rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }


    // **** PARCELABLE IMPLEMENTATION ****

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

    // CAREFUL: This must be FIFO in same order as writeToParcel.
    protected Establishment(Parcel in) {
        name = in.readString();
        rating = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(rating);
    }
}
