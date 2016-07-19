package io.agileninja.nearbypizza.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import io.agileninja.nearbypizza.model.pizzaplace.PizzaPlaceModel;

public class ResultsModel implements Parcelable {

    @SerializedName("Result")
    private PizzaPlaceModel[] pizzaPlaces;

    public ResultsModel(PizzaPlaceModel[] pizzaPlaces) {
        this.pizzaPlaces = pizzaPlaces;
    }

    public PizzaPlaceModel[] getPizzaPlaces() {
        return pizzaPlaces;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.pizzaPlaces, flags);
    }

    private ResultsModel(Parcel in) {
        this.pizzaPlaces = in.createTypedArray(PizzaPlaceModel.CREATOR);
    }

    public static final Parcelable.Creator<ResultsModel> CREATOR = new Parcelable.Creator<ResultsModel>() {
        @Override
        public ResultsModel createFromParcel(Parcel source) {
            return new ResultsModel(source);
        }

        @Override
        public ResultsModel[] newArray(int size) {
            return new ResultsModel[size];
        }
    };
}
