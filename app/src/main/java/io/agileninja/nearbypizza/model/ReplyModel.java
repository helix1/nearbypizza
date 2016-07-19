package io.agileninja.nearbypizza.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ReplyModel implements Parcelable {

    @SerializedName("query")
    private QueryModel query;

    public ReplyModel(QueryModel query) {
        this.query = query;
    }

    public QueryModel getQuery() {
        return query;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.query, flags);
    }

    private ReplyModel(Parcel in) {
        this.query = in.readParcelable(QueryModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<ReplyModel> CREATOR =
            new Parcelable.Creator<ReplyModel>() {
                @Override
                public ReplyModel createFromParcel(Parcel source) {
                    return new ReplyModel(source);
                }

                @Override
                public ReplyModel[] newArray(int size) {
                    return new ReplyModel[size];
                }
            };
}
