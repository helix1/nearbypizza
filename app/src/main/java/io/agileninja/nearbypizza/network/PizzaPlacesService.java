package io.agileninja.nearbypizza.network;


import hugo.weaving.DebugLog;
import io.agileninja.nearbypizza.model.ReplyModel;
import io.agileninja.nearbypizza.model.ResultsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PizzaPlacesService {

    interface PizzaPlacesAPI {
        @GET("yql?format=json")
        Call<ReplyModel> getPizzaPlaces(@Query("q") String query);
    }

    private PizzaPlacesAPI api;
    private Call<ReplyModel> activeCall;

    public PizzaPlacesService(YQLClient client) {
        api = client.create(PizzaPlacesAPI.class);
    }

    public void getPizzaPlaces(double latitude, double longitude,
                               final NetworkCallback<ResultsModel> callback) {
        getPizzaPlaces(latitude, longitude, callback, false);
    }

    public void getPizzaPlaces(double latitude, double longitude,
                               final NetworkCallback<ResultsModel> callback,
                               boolean isCancellable) {
        Call<ReplyModel> call = api.getPizzaPlaces(coordinatesToQuery(latitude, longitude));
        if (isCancellable) {
            if (activeCall != null) {
                // Call already exists
                return;
            }
            activeCall = call;
        }
        call.enqueue(new Callback<ReplyModel>() {
            @DebugLog
            @Override
            public void onResponse(Call<ReplyModel> call, Response<ReplyModel> response) {
                activeCall = null;
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getQuery().getResults());
                } else {
                    callback.onError("Server Error", response.errorBody().toString());
                }
            }

            @DebugLog
            @Override
            public void onFailure(Call<ReplyModel> call, Throwable t) {
                activeCall = null;
                callback.onError("Android Application Error", t.getMessage());
            }
        });
    }

    public void cancel() {
        if (activeCall != null) {
            activeCall.cancel();
            activeCall = null;
        }
    }

    private String coordinatesToQuery(double latitude, double longitude) {
//        return "select%20*%20from%20local.search%20where%20latitude%3D'" + latitude
//                + "'%20and%20query%3D'pizza'%20and%20longitude%3D'" + longitude
//                + "'&format=json&callback=";
        return "select * from local.search where latitude='" + latitude
                + "' and query='pizza' and longitude='" + longitude
                + "'";
    }
}
