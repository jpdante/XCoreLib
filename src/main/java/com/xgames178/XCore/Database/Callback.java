package com.xgames178.XCore.Database;

/**
 * Created by jpdante on 02/05/2017.
 */
public interface Callback<T> {
    public void onComplete(T result);
}