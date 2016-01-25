package com.ardev.assessment.fyberchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ardev.assessment.fyberchallenge.BEHandler.FyberRequest;
import com.ardev.assessment.fyberchallenge.BEHandler.FyberRequestHandler;
import com.ardev.assessment.fyberchallenge.BEHandler.FyberResponse;
import com.ardev.assessment.fyberchallenge.BEHandler.VolleyRequester;
import com.ardev.assessment.fyberchallenge.listeners.OnRequestCompletedListener;
import com.ardev.assessment.fyberchallenge.utils.AppLog;
import com.fyber.Fyber;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DataFormActivity extends AppCompatActivity implements Validator.ValidationListener, OnRequestCompletedListener {
    /*****************************************************************************/
    private final String LOG_TAG = "DataFormActivity";

    @NotEmpty (messageResId = R.string.requiredField)
    private EditText appIdEditText, userIdEditText, apiKeyEditText;

    private String appId, userId, apiKey;
    /*****************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_form);
        initialize();
    }//end onCreate
    /*****************************************************************************/
    private void initialize() {
        Button connectBtn;

        final Validator validator = new Validator(this);
        validator.setValidationListener(this);

        appIdEditText = (EditText) findViewById(R.id.appIdEditTxt);
        userIdEditText = (EditText) findViewById(R.id.userIdEditTxt);
        apiKeyEditText = (EditText) findViewById(R.id.apiKeyEditTxt);

        connectBtn = (Button) findViewById(R.id.connectBtn);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }//end onClick
        });//end connectButtonListener

    }//end initialize
    /*****************************************************************************/
    private void showOffersWall() {
        Intent offersWallIntent = new Intent(this, OffersWallActivity.class);
        startActivity(offersWallIntent);
    }//end showOffersWall
    /*****************************************************************************/
    @Override
    protected void onResume() {
        super.onResume();
    }//end onResume
    /*****************************************************************************/
    @Override
    public void onValidationSucceeded() {
        AppLog.d("DataFormActivity", "successful validation");
        appId = appIdEditText.getText().toString();
        userId = userIdEditText.getText().toString();
        apiKey = apiKeyEditText.getText().toString();

        FyberRequest offersWallRequest = new FyberRequest(appId, userId, apiKey);
        Fyber.Settings fyberSettings =
                Fyber.with(appId, this).withUserId(userId).withParameters(offersWallRequest.getRequestParamsMap()).
                        withSecurityToken(offersWallRequest.getSecurityHash()).start();
        AppLog.d("DataFormActivity", fyberSettings.toString());

        findViewById(R.id.formProgress).setVisibility(View.VISIBLE);
        VolleyRequester.getInstance(getApplicationContext()).
                requestJsonNwCall(new FyberRequestHandler().getOffersWall(offersWallRequest), this);
    }//end onValidationSucceeded
    /*****************************************************************************/
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        AppLog.d(LOG_TAG, "failed validation");
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }//end loop
    }//end onValidationFailed
    /*****************************************************************************/
    /*@Override
    public void onSuccess(Object response) {
        findViewById(R.id.formProgress).setVisibility(View.GONE);
        AppLog.d(LOG_TAG, "Offers ok. response = " + response);
    }//end onSuccess*/

    @Override
    public void onSuccess(FyberResponse fyberResponse) {
        findViewById(R.id.formProgress).setVisibility(View.GONE);
        AppLog.d(LOG_TAG, "Offers ok. response = " + fyberResponse.getResInJson());
        AppLog.d(LOG_TAG, "Offers ok. response = " + fyberResponse.getSignatureHeader());
    }//end onSuccess

    /*****************************************************************************/
    @Override
    public void onFail(int statusCode, String data) {
        findViewById(R.id.formProgress).setVisibility(View.GONE);
        try {
            Toast.makeText(this, new JSONObject(data).getString("message"), Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.generalErrorMessage, Toast.LENGTH_LONG).show();
        }
    }//end onFail
    /*****************************************************************************/
}//end Activity
