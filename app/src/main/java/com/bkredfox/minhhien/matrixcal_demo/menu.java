package com.bkredfox.minhhien.matrixcal_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Button btnStartCalculator = (Button) findViewById(R.id.startCalculator);
        Button btnSetting = (Button) findViewById(R.id.setting);
        Button btnExit = (Button) findViewById(R.id.exit);


    }

    public void startCalculator(View view) {
        Intent intent_calculator = new Intent(this, matrixDisplay.class);
        startActivity(intent_calculator);
    }

    public void exit(View view) {
        finish();
        System.exit(0);
    }

    public void startSetting(View view) {
        Intent intent_setting = new Intent(this, setting.class);
        startActivity(intent_setting);
    }
}
