package com.ardev.assessment.fyberchallenge.BEHandler;

import com.android.volley.toolbox.JsonArrayRequest;
import com.ardev.assessment.fyberchallenge.utils.AppLog;
import com.ardev.assessment.fyberchallenge.utils.Constants;
import com.ardev.assessment.fyberchallenge.utils.SHA1Generator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by RowanTarek on 24/01/2016.
 */
public class FyberResponse {
    /*****************************************************************************/
    private String resInJson, signatureHeader;
    private int pagesCount;
//    protected byte[] resData;
    /*****************************************************************************/
    public void setResInJson(String resInJson) {
        this.resInJson = resInJson;
    }

    public void setSignatureHeader(String signatureHeader) {
        this.signatureHeader = signatureHeader;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }
/*****************************************************************************/
    public String getResInJson() {
        return resInJson;
    }

    public int getPagesCount() {
        return pagesCount;
    }
    /*****************************************************************************/
    public boolean IsRealResponse(String apiKey){
        String apiAndResponse = resInJson + apiKey;
        AppLog.d("FybserResponse", "apiAndResponse = " + apiAndResponse);
        String responseHashKey = SHA1Generator.generateStringHash(apiAndResponse);
        AppLog.d("FyberResponse", "signature = " + signatureHeader);
        AppLog.d("FybserResponse", "response hash key= " + responseHashKey);
        return
                (responseHashKey!=null && responseHashKey.equals(signatureHeader)) ?
                        true: false ;
    }//end isRealResponse
    /*****************************************************************************/
    public ArrayList<Offer> getOffers() throws JSONException {
        JSONObject responseAsJson = new JSONObject(resInJson);
        ArrayList offersList = new ArrayList(responseAsJson.getInt("count"));
        JSONArray jsonOffersList = responseAsJson.getJSONArray("offers");
        int offersLength = jsonOffersList.length();
        for(int counter=0 ; counter<offersLength ; counter++){
            JSONObject jsonCurOffer = jsonOffersList.getJSONObject(counter);
            Offer curOffer = new Offer();
            curOffer.setTitle(jsonCurOffer.getString("title"));
            curOffer.setTeaser(jsonCurOffer.getString("teaser"));
            curOffer.setThumbnailHiResUrl(jsonCurOffer.getJSONObject("thumbnail").getString("hires"));
            curOffer.setPayout(jsonCurOffer.getString("payout"));
            offersList.add(curOffer);
        }//end loop
        return offersList;
    }//end getOffers
    /*****************************************************************************/
}//end class FyberResponse
