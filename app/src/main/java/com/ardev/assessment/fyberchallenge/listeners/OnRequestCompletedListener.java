package com.ardev.assessment.fyberchallenge.listeners;

import java.util.Map;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public interface OnRequestCompletedListener {
    public void onSuccess(Map<String, String> headers, Object response);
    public void onFail(int statusCode, String data);
}
