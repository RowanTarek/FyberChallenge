package com.ardev.assessment.fyberchallenge.listeners;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public interface OnRequestCompletedListener {
    public void onSuccess(Object response);
    public void onFail(int statusCode, String data);
}
