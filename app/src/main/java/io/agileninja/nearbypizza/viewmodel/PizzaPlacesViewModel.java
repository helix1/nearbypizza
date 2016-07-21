package io.agileninja.nearbypizza.viewmodel;


import android.databinding.Bindable;
import android.support.annotation.StringRes;

import io.agileninja.nearbypizza.R;
import io.agileninja.nearbypizza.model.ResultsModel;

public class PizzaPlacesViewModel extends BaseViewModel {

    private ResultsModel model;

    public PizzaPlacesViewModel(ResultsModel model) {
        this.model = model;
    }

    @Bindable
    @StringRes
    public int getPageTitle() {
        return R.string.places_title;
    }

    public int getNumberOfPlaces() {
        return model.getPizzaPlaces().length;
    }

    public PizzaPlaceViewModel getPizzaPlace(int index) {
        return new PizzaPlaceViewModel(model.getPizzaPlaces()[index]);
    }
}
