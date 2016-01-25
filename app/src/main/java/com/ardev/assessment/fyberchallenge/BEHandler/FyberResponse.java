package com.ardev.assessment.fyberchallenge.BEHandler;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.toolbox.JsonArrayRequest;
import com.ardev.assessment.fyberchallenge.utils.AppLog;
import com.ardev.assessment.fyberchallenge.utils.Constants;
import com.ardev.assessment.fyberchallenge.utils.SHA1Generator;
import com.fyber.Fyber;

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
public class FyberResponse implements Parcelable{
    /*****************************************************************************/
    private String resInJson, signatureHeader;
    private int pagesCount;
//    protected byte[] resData;

    /**
     *
     * Constructor to use when re-constructing object
     * from a parcel
     *
     * @param in a parcel from which to read this object
     */
    public FyberResponse(Parcel in) {
        readFromParcel(in);
    }

    public FyberResponse(){

    }
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
    @Override
    public int describeContents() {
        return 0;
    }//end describeContents
    /*****************************************************************************/

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pagesCount);
        dest.writeString(resInJson);
    }//end writeToParcel
    /*****************************************************************************/
    /**
     *
     * Called from the constructor to create this
     * object from a parcel.
     *
     * @param in parcel from which to re-create object
     */
    private void readFromParcel(Parcel in) {
        // We just need to read back each field in the order that it was written to the parcel
        pagesCount = in.readInt();
        resInJson = in.readString();
    }

    /**
     *
     * This field is needed for Android to be able to
     * create new objects, individually or as arrays.
     *
     * This also means that you can use use the default
     * constructor to create the object and use another
     * method to hyrdate it as necessary.
     *
     * I just find it easier to use the constructor.
     * It makes sense for the way my brain thinks ;-)
     *
     */
    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public FyberResponse createFromParcel(Parcel in) {
                    return new FyberResponse(in);
                }

                public FyberResponse[] newArray(int size) {
                    return new FyberResponse[size];
                }
            };

}//end class FyberResponse
