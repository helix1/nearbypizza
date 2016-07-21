package io.agileninja.nearbypizza.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Locale;

import io.agileninja.nearbypizza.PlaceDetailsActivity;
import io.agileninja.nearbypizza.R;
import io.agileninja.nearbypizza.databinding.ViewPlaceDetailsBinding;
import io.agileninja.nearbypizza.viewmodel.PlaceDetailsViewModel;


public class PlaceDetailsView extends FrameLayout {

    private ViewPlaceDetailsBinding binding;

    public PlaceDetailsView(Context context) {
        super(context);
        init(context);
    }

    public PlaceDetailsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlaceDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.view_place_details, this, true);
        binding.mapButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMap();
            }
        });
        binding.callButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }
        });
    }

    public void setPlaceDetails(PlaceDetailsViewModel viewModel) {
        binding.setDetails(viewModel);
    }

    public void goToMap() {
        PlaceDetailsViewModel details = binding.getDetails();
        if (details != null) {
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", details.getLatitude(),
                    details.getLongitude());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            getContext().startActivity(intent);
        }
    }

    public void call() {
        PlaceDetailsViewModel details = binding.getDetails();
        if (details != null && details.getPhoneNumber() != null) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + details.getPhoneNumber()));
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission
                        .CALL_PHONE}, PlaceDetailsActivity.CALL_PERMISSION);
                return;
            }
            getContext().startActivity(intent);
        }
    }
}
