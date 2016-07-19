package io.agileninja.nearbypizza.network;


public interface NetworkCallback<T> {
    void onSuccess(T response);
    void onError(String header, String message);
}
