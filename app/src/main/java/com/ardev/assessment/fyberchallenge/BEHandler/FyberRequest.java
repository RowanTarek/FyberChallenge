package com.ardev.assessment.fyberchallenge.BEHandler;

import com.ardev.assessment.fyberchallenge.utils.SHA1Generator;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by RowanTarek on 17/01/2016.
 */
public class FyberRequest {
    /*****************************************************************************/
    private Map<String, String> requestParamsMap;

    private final String APP_ID, USER_ID, API_KEY;
    private String deviceId, ip, customParameter, offerType;
/*http://api.fyber.com/feed/v1/offers.json?
&ps_time=[TIMESTAMP]
&pub0=[CUSTOM]
&google_ad_id=[GAID]
&google_ad_id_limited_tracking_enabled=[GAID ENABLED]
*/
    private enum UrlParameters {
        RESPONSE_FORMAT("format"),
        APP_ID("appid"),
        USER_ID("uid"),
        LOCALE("locale"),
        HASH_KEY("hashkey"),
        DEVICE_ID("device_id"),
        TIME_STAMP("timestamp"),
        IP("ip"),
        OFFER_TYPE("offer_types"),
        CUSTOM_PARAMETER("pub0")
        ;

        private final String param;
        UrlParameters(final String text) {
            this.param = text;
        }
        @Override
        public String toString() {
            return param;
        }
    }//end enum url params
    /*****************************************************************************/
    public FyberRequest(String appId, String userId, String apiKey) {
        this.APP_ID = appId;
        this.USER_ID = userId;
        this.API_KEY = apiKey;

        requestParamsMap = new TreeMap<String, String>();
        requestParamsMap.put(UrlParameters.APP_ID.toString(), this.APP_ID);
        requestParamsMap.put(UrlParameters.USER_ID.toString(), this.USER_ID);
        requestParamsMap.put(UrlParameters.RESPONSE_FORMAT.toString(), "json");
        requestParamsMap.put(UrlParameters.LOCALE.toString(), "en");
        requestParamsMap.put(UrlParameters.TIME_STAMP.toString(), String.valueOf((System.currentTimeMillis() / 1000)) );
    }//end constructor
    /*****************************************************************************/
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        requestParamsMap.put(UrlParameters.DEVICE_ID.toString(), this.deviceId);
    }

    public void setIp(String ip) {
        this.ip = ip;
        requestParamsMap.put(UrlParameters.IP.toString(), this.ip);
    }

    public void setCustomParameter(String customParameter) {
        this.customParameter = customParameter;
        requestParamsMap.put(UrlParameters.CUSTOM_PARAMETER.toString(), this.customParameter);
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
        requestParamsMap.put(UrlParameters.OFFER_TYPE.toString(), this.offerType);
    }
    /*****************************************************************************/
    public String getDeviceId() {
        return deviceId;
    }

    public String getIp() {
        return ip;
    }

    public String getCustomParameter() {
        return customParameter;
    }

    public String getOfferType() {
        return offerType;
    }

    public Map<String, String> getRequestParamsMap() {
        return requestParamsMap;
    }


    public String getParams(){
        return getFinalRequestParams();
    }
    /*****************************************************************************/
    /*****************************************************************************/
    private String getFinalRequestParams(){

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?,?> entry : requestParamsMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    entry.getKey().toString(),
                    entry.getValue().toString()
            ));
        }
        //adding apiKey
        sb.append("&" + API_KEY);
        String allParamsString = sb.toString();
        String requestHashKey = SHA1Generator.generateStringHash(allParamsString);
        sb.delete(sb.lastIndexOf("&"), sb.length());
        sb.append("&" + String.format("%s=%s", UrlParameters.HASH_KEY, requestHashKey) );
        return sb.toString();
    }//end generateRequestHash
    /*****************************************************************************/


}//end FyberRequest
