package com.ardev.assessment.fyberchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ardev.assessment.fyberchallenge.utils.AppLog;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.requesters.OfferWallRequester;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;

public class OffersWallActivity extends AppCompatActivity {
    /*****************************************************************************/
    private final String LOG_TAG = "OffersWallActivity";
    private final int OFFERWALL_REQUEST_CODE = 150;
    /*****************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_offers_wall);
        Fyber.with("2070", this).start();
        OfferWallRequester.create(offersWallReqCallback).request(getApplicationContext());
    }//end onCreate
    /*****************************************************************************/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        AppLog.d(LOG_TAG, "back to activity");

    }
    /*****************************************************************************/
    RequestCallback offersWallReqCallback = new RequestCallback() {

        @Override
        public void onRequestError(RequestError requestError) {
            AppLog.d(LOG_TAG, "Something went wrong with the request: " + requestError.getDescription());
        }
        //-------------------------------------------------------------------------/
        @Override
        public void onAdAvailable(Intent intent) {
            AppLog.d(LOG_TAG, "Offers are available");
            startActivityForResult(intent, OFFERWALL_REQUEST_CODE);

        }
        //-------------------------------------------------------------------------/
        @Override
        public void onAdNotAvailable(AdFormat adFormat) {
            AppLog.d(LOG_TAG, "No ad available");
            findViewById(R.id.errorTextView).setVisibility(View.VISIBLE);
        }
    };
}//end OffersWallActivity
