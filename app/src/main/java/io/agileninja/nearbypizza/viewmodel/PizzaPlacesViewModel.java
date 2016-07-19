package io.agileninja.nearbypizza.viewmodel;


import io.agileninja.nearbypizza.model.ResultsModel;

public class PizzaPlacesViewModel extends BaseViewModel {

    private ResultsModel model;

    public PizzaPlacesViewModel(ResultsModel model) {
        this.model = model;
    }

    public int getNumberOfPlaces() {
        return model.getPizzaPlaces().length;
    }

    public PizzaPlaceViewModel getPizzaPlace(int index) {
//        checkElementIndex(index, getNumberOfPlaces());
        return new PizzaPlaceViewModel(model.getPizzaPlaces()[index]);
    }
}
