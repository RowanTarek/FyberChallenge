package com.ardev.assessment.fyberchallenge.BEHandler;

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
&ip=[IP_ADDRESS]
&device_id=[DEVICE_ID]
&ps_time=[TIMESTAMP]
&pub0=[CUSTOM]
&timestamp=[UNIX_TIMESTAMP]
&offer_types=[OFFER_TYPES]
&google_ad_id=[GAID]
&google_ad_id_limited_tracking_enabled=[GAID ENABLED]
&hashkey=[HASHKEY]*/
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
//        API_KEY(""),  //no key for this value
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
        requestParamsMap.put(UrlParameters.TIME_STAMP.toString(), String.valueOf((System.currentTimeMillis()/1000)) );
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

    public String getSecurityHash(){
        return generateRequestHash(getFinalRequestParams());
    }

    public String getParams(){
        return getFinalRequestParams();
    }
    /*****************************************************************************/
    /*****************************************************************************/
    private String getFinalRequestParams(){
        /*String hashKey = requestParamsMap.entrySet().stream()
                .map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
                .reduce((p1, p2) -> p1 + "&" + p2)
                .orElse("");*/

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
        String requestHashKey = generateRequestHash(allParamsString);
        sb.delete(sb.lastIndexOf("&"), sb.length());
        sb.append("&" + String.format("%s=%s", UrlParameters.HASH_KEY, requestHashKey) );
        return sb.toString();
    }//end generateRequestHash
    /*****************************************************************************/
    /**
     * @Author  <a href="http://stackoverflow.com/users/419075/amir-raminfar">Amir Raminfar</a>
     */
    private String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }//end convertToHex
    /*****************************************************************************/
    /**
     * @Author  <a href="http://stackoverflow.com/users/419075/amir-raminfar">Amir Raminfar</a>
     */
    private String generateRequestHash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = text.getBytes();
            md.update(bytes, 0, bytes.length);
            byte[] sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } /*catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return null;
    }//end generateRequestHash

}//end FyberRequest
