package com.bkredfox.minhhien.matrixcal_demo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class save extends Activity {

    String saveMat;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save);

        Intent intent = getIntent();
        if (intent != null)
            saveMat = intent.getStringExtra("saveMat");

        pref = getSharedPreferences("my_matrix", MODE_PRIVATE);
        editor = pref.edit();


    }

    public void btnBack(View view) {
        this.finish();
    }

    public void btnStorageSave_onClick(View view) {

        switch (view.getId()) {
            case R.id.matA: {
                //TODO: alert dialog if exists
                editor.putString("matA", saveMat);
                break;
            }
            case R.id.matB: {
                //TODO: alert dialog if exists
                editor.putString("matB", saveMat);
                break;
            }
            case R.id.matC: {
                //TODO: alert dialog if exists
                editor.putString("matC", saveMat);
                break;
            }
            case R.id.matD: {
                //TODO: alert dialog if exists
                editor.putString("matD", saveMat);
                break;
            }
            case R.id.matE: {
                //TODO: alert dialog if exists
                editor.putString("matE", saveMat);
                break;
            }
            case R.id.matF: {
                //TODO: alert dialog if exists
                editor.putString("matF", saveMat);
                break;
            }
            case R.id.matG: {
                //TODO: alert dialog if exists
                editor.putString("matG", saveMat);
                break;
            }
            case R.id.matH: {
                //TODO: alert dialog if exists
                editor.putString("matH", saveMat);
                break;
            }
            case R.id.matI: {
                //TODO: alert dialog if exists
                editor.putString("matI", saveMat);
                break;
            }
        }
        editor.commit();
        this.finish();

    }
}
