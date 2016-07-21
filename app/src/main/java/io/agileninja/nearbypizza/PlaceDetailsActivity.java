package io.agileninja.nearbypizza;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.agileninja.nearbypizza.databinding.ActivityPlaceDetailsBinding;
import io.agileninja.nearbypizza.model.pizzaplace.PizzaPlaceModel;
import io.agileninja.nearbypizza.util.Constant;
import io.agileninja.nearbypizza.viewmodel.PlaceDetailsViewModel;

public class PlaceDetailsActivity extends AppCompatActivity {

    public static final int CALL_PERMISSION = 911;
    private ActivityPlaceDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_place_details);
        Intent intent = getIntent();
        PizzaPlaceModel placeModel = intent.getParcelableExtra(Constant.BUNDLE_PLACE_MODEL);
        binding.setDetails(new PlaceDetailsViewModel(placeModel));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CALL_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    binding.placeDetails.call();
                }
                break;
        }
    }
}
