package io.agileninja.nearbypizza.model.pizzaplace;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CategoryModel implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("content")
    private String content;

    public CategoryModel(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.content);
    }

    private CategoryModel(Parcel in) {
        this.id = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<CategoryModel> CREATOR =
            new Parcelable.Creator<CategoryModel>() {
                @Override
                public CategoryModel createFromParcel(Parcel source) {
                    return new CategoryModel(source);
                }

                @Override
                public CategoryModel[] newArray(int size) {
                    return new CategoryModel[size];
                }
            };
}
