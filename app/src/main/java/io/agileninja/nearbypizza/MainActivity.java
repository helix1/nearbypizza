package io.agileninja.nearbypizza;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import io.agileninja.nearbypizza.databinding.ActivityMainBinding;
import io.agileninja.nearbypizza.model.ResultsModel;
import io.agileninja.nearbypizza.network.NetworkCallback;
import io.agileninja.nearbypizza.network.PizzaPlacesService;
import io.agileninja.nearbypizza.network.YQLClient;
import io.agileninja.nearbypizza.viewmodel.PizzaPlacesViewModel;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int PERMISSION_REQUEST_LOCATION = 1234;
    private ActivityMainBinding binding;
    private LocationManager locationManager;
    private String provider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        setSupportActionBar(binding.toolbar);
//        binding.collapsingToolbar.setTitle(getString(R.string.places_title));
        checkPermissionsAndInitiateService();
    }

    private void getPlaces(double latitude, double longitude) {
        PizzaPlacesService service = new PizzaPlacesService(new YQLClient());
        service.getPizzaPlaces(latitude, longitude, new NetworkCallback<ResultsModel>() {

            @Override
            public void onSuccess(ResultsModel response) {
                binding.setPlaces(new PizzaPlacesViewModel (response));
            }


            @Override
            public void onError(String header, String message) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(header)
                        .setMessage(message)
                        .create()
                        .show();
            }
        });
    }



    private void checkPermissionsAndInitiateService() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            onLocationChanged(location);
            getPlaces(location.getLatitude(), location.getLongitude());
        } else {
            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(getString(R.string.location_unavailable))
                    .setNeutralButton(R.string.ok, null)
                    .create()
                    .show();
        }
    }


    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                && ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            new AlertDialog.Builder(MainActivity.this)
                    .setMessage(getString(R.string.permission_location_request))
                    .create()
                    .show();

        } else {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },
                    PERMISSION_REQUEST_LOCATION);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
            return;
        }
        locationManager.removeUpdates(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        boolean permissionGranted = true;
        switch (requestCode) {
            case PERMISSION_REQUEST_LOCATION:
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        permissionGranted = false;
                    }
                }
                if (permissionGranted) {
                    checkPermissionsAndInitiateService();
                }
                break;
        }
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
