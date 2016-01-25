package com.ardev.assessment.fyberchallenge.BEHandler;

import com.ardev.assessment.fyberchallenge.utils.AppLog;
import com.ardev.assessment.fyberchallenge.utils.Constants;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by RowanTarek on 24/01/2016.
 */
public class FyberResponse {
    /*****************************************************************************/
    private String resInJson, /*signature,*/ signatureHeader;
    private int pagesCount;
    protected byte[] resData;
    /*****************************************************************************/
    public void setResInJson(String resInJson) {
        this.resInJson = resInJson;
    }

   /* public void setSignature(String signature) {
        this.signature = signature;
    }*/

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

    /*public String getSignature() {
        return signature;
    }*/

   /* public String getSignatureHeader() {
        return signatureHeader;
    }*/

    public int getPagesCount() {
        return pagesCount;
    }
    /*****************************************************************************/
    public boolean IsRealResponse(){
        // TODO: 25/01/2016 from user input not static
        String apiAndResponse = resInJson + "1c915e3b5d42d05136185030892fbb846c278927";
        AppLog.d("FybserResponse", "apiAndResponse = " + apiAndResponse);
        String responseHashKey = generateResponseHash(apiAndResponse);
        AppLog.d("FyberResponse", "signature = " + signatureHeader);
        AppLog.d("FybserResponse", "response hash key= " + responseHashKey);
        return
                (responseHashKey!=null && responseHashKey.equals(signatureHeader)) ?
                        true: false ;
    }//end isRealResponse
    /*****************************************************************************/
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
    }

    private String generateResponseHash(String text) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            md.update(text.getBytes()/*("UTF-8")*/, 0, text.length());
            byte[] sha1hash = md.digest(text.getBytes());
            return convertToHex(sha1hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } /*catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        return null;
    }
}//end class FyberResponse
