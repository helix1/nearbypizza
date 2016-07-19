package io.agileninja.nearbypizza.viewmodel;


import android.databinding.Bindable;

import io.agileninja.nearbypizza.model.pizzaplace.PizzaPlaceModel;

public class PizzaPlaceViewModel extends BaseViewModel {

    private PizzaPlaceModel model;

    public PizzaPlaceViewModel(PizzaPlaceModel model) {
        this.model = model;
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
}
