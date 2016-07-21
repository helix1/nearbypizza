package io.agileninja.nearbypizza.model.pizzaplace;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PizzaPlaceModel implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Address")
    private String address;
    @SerializedName("City")
    private String city;
    @SerializedName("State")
    private String state;
    @SerializedName("Phone")
    private String phoneNumber;
    @SerializedName("Rating")
    private RatingModel rating;
    @SerializedName("Distance")
    private String distance;
    @SerializedName("Latitude")
    private double latitude;
    @SerializedName("Longitude")
    private double longitude;
    @SerializedName("ClickUrl")
    private String url;
    @SerializedName("MapUrl")
    private String mapUrl;
    @SerializedName("BusinessClickUrl")
    private String businessUrl;

    public PizzaPlaceModel(String id, String title, String address, String city, String state,
                           String phoneNumber, RatingModel rating, String distance,
                           double latitude, double longitude, String url, String mapUrl,
                           String businessUrl) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phoneNumber = phoneNumber;
        this.rating = rating;
        this.distance = distance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.url = url;
        this.mapUrl = mapUrl;
        this.businessUrl = businessUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RatingModel getRating() {
        return rating;
    }

    public String getDistance() {
        return distance;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getUrl() {
        return url;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public String getBusinessUrl() {
        return businessUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.address);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.phoneNumber);
        dest.writeParcelable(this.rating, flags);
        dest.writeString(this.distance);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.url);
        dest.writeString(this.mapUrl);
        dest.writeString(this.businessUrl);
    }

    private PizzaPlaceModel(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.address = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.phoneNumber = in.readString();
        this.rating = in.readParcelable(RatingModel.class.getClassLoader());
        this.distance = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.url = in.readString();
        this.mapUrl = in.readString();
        this.businessUrl = in.readString();
    }

    public static final Parcelable.Creator<PizzaPlaceModel> CREATOR =
            new Parcelable.Creator<PizzaPlaceModel>() {
                @Override
                public PizzaPlaceModel createFromParcel(Parcel source) {
                    return new PizzaPlaceModel(source);
                }

                @Override
                public PizzaPlaceModel[] newArray(int size) {
                    return new PizzaPlaceModel[size];
                }
            };
}
