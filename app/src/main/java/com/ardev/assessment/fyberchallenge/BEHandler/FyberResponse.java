package com.ardev.assessment.fyberchallenge.BEHandler;

/**
 * Created by RowanTarek on 24/01/2016.
 */
public class FyberResponse {
    /*****************************************************************************/
    private String resInJson, /*signature,*/ signatureHeader;
    private int pagesCount;
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

    public String getSignatureHeader() {
        return signatureHeader;
    }

    public int getPagesCount() {
        return pagesCount;
    }
    /*****************************************************************************/
    /**
     *
     * @return
     */
    public boolean IsRealResponse(){
        return false;
    }//end isRealResponse
    /*****************************************************************************/

}//end class FyberResponse
