package io.agileninja.nearbypizza.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class QueryModel implements Parcelable {

    @SerializedName("results")
    private ResultsModel results;

    public QueryModel(ResultsModel results) {
        this.results = results;
    }

    public ResultsModel getResults() {
        return results;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.results, flags);
    }

    private QueryModel(Parcel in) {
        this.results = in.readParcelable(ResultsModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<QueryModel> CREATOR =
            new Parcelable.Creator<QueryModel>() {
                @Override
                public QueryModel createFromParcel(Parcel source) {
                    return new QueryModel(source);
                }

                @Override
                public QueryModel[] newArray(int size) {
                    return new QueryModel[size];
                }
            };
}
