package com.ardev.assessment.fyberchallenge.BEHandler;

/**
 * Created by RowanTarek on 17/01/2016.
 */
public class FyberRequestHandler {
    /*****************************************************************************/
    private final String OFFERS_URL = "http://api.fyber.com/feed/v1/offers.json?";
    /*****************************************************************************/
    public String getOffersWall(FyberRequest requestToPerform){
        return  OFFERS_URL + requestToPerform.getParams();
    }
}//end class FyberRequestHandler
