package red.theodo.restauranthygienechecker1453831;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by theo on 06/03/18.
 */

public class SearchDetails implements Parcelable {
    private boolean advanced;
    private String query;
    private String type;
    private String rating;
    private String region;
    private String localAuthority;

    public SearchDetails(boolean advanced, String query, String type, String rating, String region, String localAuthority){
        this.advanced = advanced;
        this.query = query;
        this.type = type;
        this.rating = rating;
        this.region = region;
        this.localAuthority = localAuthority;
    }

    protected SearchDetails(Parcel in) {
        advanced = in.readByte() != 0;
        query = in.readString();
        type = in.readString();
        rating = in.readString();
        region = in.readString();
        localAuthority = in.readString();
    }

    public static final Creator<SearchDetails> CREATOR = new Creator<SearchDetails>() {
        @Override
        public SearchDetails createFromParcel(Parcel in) {
            return new SearchDetails(in);
        }

        @Override
        public SearchDetails[] newArray(int size) {
            return new SearchDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (advanced ? 1 : 0));
        parcel.writeString(query);
        parcel.writeString(type);
        parcel.writeString(rating);
        parcel.writeString(region);
        parcel.writeString(localAuthority);
    }

    public boolean getAdvanced(){
        return advanced;
    }

    public String getQuery() {
        return query;
    }

    public String getType() {
        return type;
    }

    public String getRating() {
        return rating;
    }

    public String getRegion() {
        return region;
    }

    public String getLocalAuthority() {
        return localAuthority;
    }
}
