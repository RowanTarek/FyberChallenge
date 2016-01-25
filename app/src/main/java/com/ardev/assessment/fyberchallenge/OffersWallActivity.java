package com.ardev.assessment.fyberchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ardev.assessment.fyberchallenge.BEHandler.FyberResponse;
import com.ardev.assessment.fyberchallenge.BEHandler.Offer;
import com.ardev.assessment.fyberchallenge.adapters.ItemsAdapter;
import com.ardev.assessment.fyberchallenge.utils.AppLog;

import org.json.JSONException;

import java.util.ArrayList;

public class OffersWallActivity extends AppCompatActivity {
    /*****************************************************************************/
    private final String LOG_TAG = "OffersWallActivity";
    /*****************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_offers_wall);
        FyberResponse wallResponse = getIntent().getParcelableExtra("response");
        AppLog.d(LOG_TAG, wallResponse.getResInJson());
        try {
            populateOffers(wallResponse.getOffers());
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.dataParsingError, Toast.LENGTH_SHORT).show();
        }
    }//end onCreate
    /*****************************************************************************/

    private void populateOffers(ArrayList<Offer> offers) {
        RecyclerView offersRecycler = (RecyclerView) findViewById(R.id.itemsRecycler);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        offersRecycler.setLayoutManager(recyclerLayoutManager);
        ItemsAdapter adapter = new ItemsAdapter(offers);
        offersRecycler.setAdapter(adapter);
    }//end populateOffers
    /*****************************************************************************/
    /*****************************************************************************/

}//end OffersWallActivity
