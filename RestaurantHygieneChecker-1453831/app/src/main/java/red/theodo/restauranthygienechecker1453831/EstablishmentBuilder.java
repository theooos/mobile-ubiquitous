package red.theodo.restauranthygienechecker1453831;

/**
 * Created by theo on 07/03/18.
 */

final class EstablishmentBuilder {
    private String name = "-1";
    private String businessType =  "-1";
    private String address = "-1";
    private String localAuthorityName = "-1";
    private String localAuthorityEmailAddress = "-1";
    private String rating = "-1";
    private long ratingDate = -1;
    private float longitude = 0;
    private float latitude = 0;
    private float distance = 0;

    private EstablishmentBuilder() {
    }

    public static EstablishmentBuilder anEstablishment() {
        return new EstablishmentBuilder();
    }

    public EstablishmentBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EstablishmentBuilder withBusinessType(String businessType) {
        this.businessType = businessType;
        return this;
    }

    public EstablishmentBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public EstablishmentBuilder withLocalAuthorityName(String localAuthorityName) {
        this.localAuthorityName = localAuthorityName;
        return this;
    }

    public EstablishmentBuilder withLocalAuthorityEmailAddress(String localAuthorityEmailAddress) {
        this.localAuthorityEmailAddress = localAuthorityEmailAddress;
        return this;
    }

    public EstablishmentBuilder withRating(String rating) {
        this.rating = rating;
        return this;
    }

    public EstablishmentBuilder withRatingDate(long ratingDate) {
        this.ratingDate = ratingDate;
        return this;
    }

    public EstablishmentBuilder withLongitude(float longitude) {
        this.longitude = longitude;
        return this;
    }

    public EstablishmentBuilder withLatitude(float latitude) {
        this.latitude = latitude;
        return this;
    }

    public EstablishmentBuilder withDistance(float distance) {
        this.distance = distance;
        return this;
    }

    public Establishment build() {
        return new Establishment(name, businessType, address, localAuthorityName, localAuthorityEmailAddress, rating, ratingDate, longitude, latitude, distance);
    }
}
