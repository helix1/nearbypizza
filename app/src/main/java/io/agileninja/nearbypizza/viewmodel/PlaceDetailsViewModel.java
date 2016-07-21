package io.agileninja.nearbypizza.viewmodel;


import android.databinding.Bindable;

import io.agileninja.nearbypizza.model.pizzaplace.PizzaPlaceModel;

public class PlaceDetailsViewModel extends BaseViewModel {

    private PizzaPlaceModel model;

    public PlaceDetailsViewModel(PizzaPlaceModel placeModel) {
        this.model = placeModel;
    }

    @Bindable
    public String getPageTitle() {
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

    public double getLatitude() {
        return model.getLatitude();
    }

    public double getLongitude() {
        return model.getLongitude();
    }
}
