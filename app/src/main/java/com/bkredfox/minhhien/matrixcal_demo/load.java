package com.bkredfox.minhhien.matrixcal_demo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class load extends Activity {

    String loadMat;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        pref = getSharedPreferences("my_matrix", MODE_PRIVATE);
    }

    void setResult() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("loadMat", loadMat);
        setResult(RESULT_OK, resultIntent);
        this.finish();
    }

    public void btnStorageLoad_onClick(View view) {
        switch (view.getId()) {
            case R.id.matA_load: {
                loadMat = pref.getString("matA", null);
                if (loadMat != null) {

                    setResult();

                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matB_load: {
                loadMat = pref.getString("matB", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matC_load: {
                loadMat = pref.getString("matC", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matD_load: {
                loadMat = pref.getString("matD", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matE_load: {
                loadMat = pref.getString("matE", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matF_load: {

                loadMat = pref.getString("matF", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matG_load: {
                loadMat = pref.getString("matG", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matH_load: {
                loadMat = pref.getString("matH", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
            case R.id.matI_load: {
                loadMat = pref.getString("matI", null);
                if (loadMat != null) {
                    setResult();
                } else {
                    //TODO: dialog or toast for not exist
                }
                break;
            }
        }
    }
}
