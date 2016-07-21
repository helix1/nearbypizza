package io.agileninja.nearbypizza.viewmodel;


import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import io.agileninja.nearbypizza.model.pizzaplace.PizzaPlaceModel;

public class PizzaPlaceViewModel extends BaseViewModel implements Parcelable {

    private PizzaPlaceModel model;

    public PizzaPlaceViewModel(PizzaPlaceModel model) {
        this.model = model;
    }

    public PizzaPlaceModel getModel() {
        return model;
    }

    @Bindable
    public String getName() {
        return model.getTitle();
    }

    @Bindable
    public String getAddress() {
        return model.getAddress();
    }

    @Bindable
    public String getCity() {
        return model.getCity();
    }

    @Bindable
    public String getState() {
        return model.getState();
    }

    @Bindable
    public String getPhoneNumber() {
        return model.getPhoneNumber();
    }

    @Bindable
    public String getDistance() {
        return model.getDistance();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.model, flags);
    }

    private PizzaPlaceViewModel(Parcel in) {
        this.model = in.readParcelable(PizzaPlaceModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<PizzaPlaceViewModel> CREATOR =
            new Parcelable.Creator<PizzaPlaceViewModel>() {
                @Override
                public PizzaPlaceViewModel createFromParcel(Parcel source) {
                    return new PizzaPlaceViewModel(source);
                }

                @Override
                public PizzaPlaceViewModel[] newArray(int size) {
                    return new PizzaPlaceViewModel[size];
                }
            };
}
