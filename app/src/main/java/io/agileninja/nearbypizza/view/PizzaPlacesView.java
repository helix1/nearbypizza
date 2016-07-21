package io.agileninja.nearbypizza.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.agileninja.nearbypizza.PlaceDetailsActivity;
import io.agileninja.nearbypizza.R;
import io.agileninja.nearbypizza.databinding.ViewPizzaPlaceBinding;
import io.agileninja.nearbypizza.util.Constant;
import io.agileninja.nearbypizza.viewmodel.PizzaPlacesViewModel;


public class PizzaPlacesView extends RecyclerView {

    private PizzaPlacesAdapter adapter;

    public PizzaPlacesView(Context context) {
        super(context);
        init(context);
    }

    public PizzaPlacesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PizzaPlacesView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setLayoutManager(new LinearLayoutManager(context));
        adapter = new PizzaPlacesAdapter();
        setAdapter(adapter);
    }

    public void setPlaces(PizzaPlacesViewModel places) {
        adapter.setPizzaPlaces(places);
    }

    private class PizzaPlacesAdapter extends Adapter<PizzaPlacesAdapter.PizzaPlaceViewHolder> {

        private PizzaPlacesViewModel places;

        public void setPizzaPlaces(PizzaPlacesViewModel places) {
            this.places = places;
            notifyDataSetChanged();
        }

        @Override
        public PizzaPlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewPizzaPlaceBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.view_pizza_place, parent, false);
            return new PizzaPlaceViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(PizzaPlaceViewHolder holder, int position) {
            holder.binding.setPlace(places.getPizzaPlace(position));
        }

        @Override
        public int getItemCount() {
            return places == null ? 0 : places.getNumberOfPlaces();
        }

        class PizzaPlaceViewHolder extends ViewHolder implements OnClickListener {

            ViewPizzaPlaceBinding binding;

            public PizzaPlaceViewHolder(ViewPizzaPlaceBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                binding.getRoot().setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PlaceDetailsActivity.class);
                intent.putExtra(Constant.BUNDLE_PLACE_MODEL, binding.getPlace().getModel());
                view.getContext().startActivity(intent);
            }
        }
    }
}
