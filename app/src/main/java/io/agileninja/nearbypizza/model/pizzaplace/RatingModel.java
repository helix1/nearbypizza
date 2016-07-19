package io.agileninja.nearbypizza.model.pizzaplace;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RatingModel implements Parcelable {
    @SerializedName("AverageRating")
    private String averageRating;
    @SerializedName("TotalRatings")
    private String totalRatings;
    @SerializedName("TotalReviews")
    private String totalReviews;
    @SerializedName("LastReviewDate")
    private String lastReviewDate;
    @SerializedName("LastReviewIntro")
    private String lastReviewIntro;

    public RatingModel(String averageRating, String totalRatings, String totalReviews,
                       String lastReviewDate, String lastReviewIntro) {
        this.averageRating = averageRating;
        this.totalRatings = totalRatings;
        this.totalReviews = totalReviews;
        this.lastReviewDate = lastReviewDate;
        this.lastReviewIntro = lastReviewIntro;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public String getTotalRatings() {
        return totalRatings;
    }

    public String getTotalReviews() {
        return totalReviews;
    }

    public String getLastReviewDate() {
        return lastReviewDate;
    }

    public String getLastReviewIntro() {
        return lastReviewIntro;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.averageRating);
        dest.writeString(this.totalRatings);
        dest.writeString(this.totalReviews);
        dest.writeString(this.lastReviewDate);
        dest.writeString(this.lastReviewIntro);
    }

    private RatingModel(Parcel in) {
        this.averageRating = in.readString();
        this.totalRatings = in.readString();
        this.totalReviews = in.readString();
        this.lastReviewDate = in.readString();
        this.lastReviewIntro = in.readString();
    }

    public static final Parcelable.Creator<RatingModel> CREATOR =
            new Parcelable.Creator<RatingModel>() {
                @Override
                public RatingModel createFromParcel(Parcel source) {
                    return new RatingModel(source);
                }

                @Override
                public RatingModel[] newArray(int size) {
                    return new RatingModel[size];
                }
            };
}
