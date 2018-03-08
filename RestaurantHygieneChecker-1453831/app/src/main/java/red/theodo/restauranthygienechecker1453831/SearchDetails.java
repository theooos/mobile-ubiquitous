package red.theodo.restauranthygienechecker1453831;

/**
 * Created by theo on 06/03/18.
 */

public class SearchDetails {

    private SearchMode mode;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String maxDistanceLimit;
    private String businessTypeId;
    private String schemeTypeKey;
    private String ratingKey;
    private String ratingOperatorKey;
    private String localAuthorityId;
    private String countryId;
    private String sortOptionKey;
    private String pageNumber;
    private String pageSize;

    public SearchDetails(SearchMode mode, String name, String address, String latitude, String longitude, String maxDistanceLimit, String businessTypeId, String schemeTypeKey, String ratingKey, String ratingOperatorKey, String localAuthorityId, String countryId, String sortOptionKey, String pageNumber, String pageSize) {
        this.mode = mode;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.maxDistanceLimit = maxDistanceLimit;
        this.businessTypeId = businessTypeId;
        this.schemeTypeKey = schemeTypeKey;
        this.ratingKey = ratingKey;
        this.ratingOperatorKey = ratingOperatorKey;
        this.localAuthorityId = localAuthorityId;
        this.countryId = countryId;
        this.sortOptionKey = sortOptionKey;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public String generateUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append("http://api.ratings.food.gov.uk/Establishments?");

        switch (mode) {
            case Local:
                builder.append(String.format("longitude=%s&", longitude));
                builder.append(String.format("latitude=%s&", latitude));
                builder.append(String.format("pageSize=%s&", pageSize));
                builder.append(String.format("sortOptionKey=%s&", sortOptionKey));
                break;

            case Simple:
                if (address == null)
                    builder.append(String.format("name=%s&", name));
                else
                    builder.append(String.format("address=%s&", address));
                builder.append(String.format("sortOptionKey=%s&", sortOptionKey));
                builder.append(String.format("pageSize=%s&", pageSize));
                break;

            case Advanced:
                if (longitude != null){
                    builder.append(String.format("longitude=%s&", longitude));
                    builder.append(String.format("latitude=%s&", latitude));
                    if(maxDistanceLimit != null)
                        builder.append(String.format("maxDistanceLimit=%s&", maxDistanceLimit));
                } else {
                    if (address == null)
                        builder.append(String.format("name=%s&", name));
                    else
                        builder.append(String.format("address=%s&", address));
                }

                if(sortOptionKey != null)
                    builder.append(String.format("sortOptionKey=%s&", sortOptionKey));
                if(pageSize != null)
                    builder.append(String.format("pageSize=%s&", pageSize));
                if(businessTypeId != null)
                    builder.append(String.format("businessTypeId=%s&", businessTypeId));
                if (ratingKey != null)
                    builder.append(String.format("ratingKey=%s&", ratingKey));
                if(localAuthorityId != null)
                    builder.append(String.format("localAuthorityId=%s&", localAuthorityId));
        }

        return builder.toString();
    }
}
