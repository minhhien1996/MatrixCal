package com.bkredfox.minhhien.matrixcal_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class setting extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
    }

    public void applySetting(View view){
        //TODO: save setting

        this.finish();
    }

    public void discardSetting(View view){
        //TODO: discard setting

        this.finish();
    }
}
