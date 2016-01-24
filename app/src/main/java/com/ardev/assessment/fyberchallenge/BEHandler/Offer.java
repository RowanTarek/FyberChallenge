package com.ardev.assessment.fyberchallenge.BEHandler;

/**
 * Created by RowanTarek on 24/01/2016.
 */
public class Offer {
    /*****************************************************************************/
    private String title, teaser, thumbnailHiResUrl, payout;
    /*****************************************************************************/
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public void setThumbnailHiResUrl(String thumbnailHiResUrl) {
        this.thumbnailHiResUrl = thumbnailHiResUrl;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }
    /*****************************************************************************/
    public String getTitle() {
        return title;
    }

    public String getTeaser() {
        return teaser;
    }

    public String getThumbnailHiResUrl() {
        return thumbnailHiResUrl;
    }

    public String getPayout() {
        return payout;
    }
    /*****************************************************************************/
    /*****************************************************************************/
}//end Offer
