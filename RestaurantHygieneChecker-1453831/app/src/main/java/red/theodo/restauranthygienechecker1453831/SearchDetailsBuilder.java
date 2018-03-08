package red.theodo.restauranthygienechecker1453831;

/**
 * Created by theo on 07/03/18.
 */

final class SearchDetailsBuilder {
    private boolean advanced;
    private String query;
    private String type;
    private String rating;
    private String localAuthority;

    private SearchDetailsBuilder() {
    }

    public static SearchDetailsBuilder aSearchDetails() {
        return new SearchDetailsBuilder();
    }

    public SearchDetailsBuilder withAdvanced(boolean advanced) {
        this.advanced = advanced;
        return this;
    }

    public SearchDetailsBuilder withQuery(String query) {
        this.query = query;
        return this;
    }

    public SearchDetailsBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public SearchDetailsBuilder withRating(String rating) {
        this.rating = rating;
        return this;
    }

    public SearchDetailsBuilder withLocalAuthority(String localAuthority) {
        this.localAuthority = localAuthority;
        return this;
    }

    public SearchDetails build() {
        return new SearchDetails(advanced, query, type, rating, localAuthority);
    }
}
