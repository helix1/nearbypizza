package io.agileninja.nearbypizza.model.pizzaplace;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CategoriesModel implements Parcelable {

    @SerializedName("Category")
    private CategoryModel[] categories;

    public CategoriesModel(CategoryModel[] categories) {
        this.categories = categories;
    }

    public CategoryModel[] getCategories() {
        return categories;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.categories, flags);
    }

    private CategoriesModel(Parcel in) {
        this.categories = in.createTypedArray(CategoryModel.CREATOR);
    }

    public static final Parcelable.Creator<CategoriesModel> CREATOR =
            new Parcelable.Creator<CategoriesModel>() {
                @Override
                public CategoriesModel createFromParcel(Parcel source) {
                    return new CategoriesModel(source);
                }

                @Override
                public CategoriesModel[] newArray(int size) {
                    return new CategoriesModel[size];
                }
            };
}
