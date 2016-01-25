package com.ardev.assessment.fyberchallenge.listeners;

import com.ardev.assessment.fyberchallenge.BEHandler.FyberRequest;
import com.ardev.assessment.fyberchallenge.BEHandler.FyberResponse;

import java.util.Map;

/**
 * Created by RowanTarek on 10/11/2015.
 */
public interface OnRequestCompletedListener {
//    public void onSuccess(Map<String, String> headers, Object response);
    public void onSuccess(FyberResponse fyberResponse);
    public void onFail(int statusCode, String data);
}
