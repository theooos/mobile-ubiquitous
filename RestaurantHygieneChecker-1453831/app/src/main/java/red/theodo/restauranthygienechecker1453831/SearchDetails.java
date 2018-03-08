package red.theodo.restauranthygienechecker1453831;

/**
 * Created by theo on 06/03/18.
 */

public class SearchDetails {

//    private String

    private boolean advanced;
    private String query;
    private String type;
    private String rating;
    private String localAuthority;

    public SearchDetails(boolean advanced, String query, String type, String rating, String localAuthority) {
        this.advanced = advanced;
        this.query = query;
        this.type = type;
        this.rating = rating;
        this.localAuthority = localAuthority;
    }

    public String generateUrl(){
        return "";
    }
}
