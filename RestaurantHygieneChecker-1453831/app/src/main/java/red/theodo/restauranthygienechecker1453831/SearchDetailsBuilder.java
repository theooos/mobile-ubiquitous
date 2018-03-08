package red.theodo.restauranthygienechecker1453831;

/**
 * Created by theo on 08/03/18.
 */

final class SearchDetailsBuilder {
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

    private SearchDetailsBuilder() {
    }

    public static SearchDetailsBuilder aSearchDetails() {
        return new SearchDetailsBuilder();
    }

    public SearchDetailsBuilder withMode(SearchMode mode) {
        this.mode = mode;
        return this;
    }

    public SearchDetailsBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public SearchDetailsBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public SearchDetailsBuilder withLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public SearchDetailsBuilder withLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public SearchDetailsBuilder withMaxDistanceLimit(String maxDistanceLimit) {
        this.maxDistanceLimit = maxDistanceLimit;
        return this;
    }

    public SearchDetailsBuilder withBusinessTypeId(String businessTypeId) {
        this.businessTypeId = businessTypeId;
        return this;
    }

    public SearchDetailsBuilder withSchemeTypeKey(String schemeTypeKey) {
        this.schemeTypeKey = schemeTypeKey;
        return this;
    }

    public SearchDetailsBuilder withRatingKey(String ratingKey) {
        this.ratingKey = ratingKey;
        return this;
    }

    public SearchDetailsBuilder withRatingOperatorKey(String ratingOperatorKey) {
        this.ratingOperatorKey = ratingOperatorKey;
        return this;
    }

    public SearchDetailsBuilder withLocalAuthorityId(String localAuthorityId) {
        this.localAuthorityId = localAuthorityId;
        return this;
    }

    public SearchDetailsBuilder withCountryId(String countryId) {
        this.countryId = countryId;
        return this;
    }

    public SearchDetailsBuilder withSortOptionKey(String sortOptionKey) {
        this.sortOptionKey = sortOptionKey;
        return this;
    }

    public SearchDetailsBuilder withPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public SearchDetailsBuilder withPageSize(String pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public SearchDetails build() {
        return new SearchDetails(mode, name, address, latitude, longitude, maxDistanceLimit, businessTypeId, schemeTypeKey, ratingKey, ratingOperatorKey, localAuthorityId, countryId, sortOptionKey, pageNumber, pageSize);
    }
}
