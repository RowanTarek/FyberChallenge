package com.ardev.assessment.fyberchallenge.BEHandler;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ardev.assessment.fyberchallenge.listeners.OnRequestCompletedListener;
import com.ardev.assessment.fyberchallenge.utils.AppLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


/**
 * Created by RowanTarek on 10/11/2015.
 */
public class VolleyRequester {
    private  final RequestQueue queue ;
    private  static VolleyRequester _instance;
    private final String LOG_TAG = "VolleyRequester";
    /*****************************************************************************/
    private VolleyRequester(Context ctx){
        queue = Volley.newRequestQueue(ctx);
    }//end constructor
    /*****************************************************************************/
    public static VolleyRequester getInstance(Context ctx){
        return _instance == null ? new VolleyRequester(ctx) :  _instance;
    }//end initialize
    /*****************************************************************************/
    public void requestJsonNwCall(String url, final OnRequestCompletedListener requestCompletedListener){
        AppLog.d(LOG_TAG, "in requestJsonNwCall with url = " + url) ;

        Response.ErrorListener errorListener = new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                AppLog.d(LOG_TAG, "in requestJsonNwCall failed = " + error.networkResponse.statusCode) ;
                requestCompletedListener.onFail(error.networkResponse.statusCode,
                        new String(error.networkResponse.data));
            }//end onError
        };


        final Response.Listener<FyberResponse> responseListener = new Response.Listener<FyberResponse>() {

            @Override
            public void onResponse(FyberResponse response) {
                AppLog.d(LOG_TAG, "in requestJsonNwCall with response = " + response) ;
                requestCompletedListener.onSuccess(response);
            }//end onResponse
        };

        Request jsObjRequest = new Request
                (Request.Method.GET, url, /*null, responseListener ,*/ errorListener)
        {
//            private Map<String, String> responseHeaders;
            @Override
            public int compareTo(Object another) {
                return 0;
            }

            @Override
            protected Response parseNetworkResponse(NetworkResponse response) {
//                responseHeaders = response.headers;
                try {
                    FyberResponse fyberResponse = new FyberResponse();
                    fyberResponse.setSignatureHeader(response.headers.get("X-Sponsorpay-Response-Signature"));
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                    fyberResponse.setResInJson(jsonString);
                    return Response.success(fyberResponse,  HttpHeaderParser.parseCacheHeaders(response));

                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }

            }//end parseNetworkResponse

            @Override
            protected void deliverResponse(Object response) {
                responseListener.onResponse((FyberResponse) response);
            }
           /* //Override parseNetworkResponse to get header
            private Map<String, String> responseHeaders;
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                responseHeaders = response.headers;
                return super.parseNetworkResponse(response);
            }//end parseNetworkResponse*/
        };//end volleyJSONRequester

        queue.add(jsObjRequest);
    }//end requestJsonNwCall
    /*****************************************************************************/

}//end VolleyRequester
