package com.bkredfox.minhhien.matrixcal_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class functions extends Activity {

    Button btnTrace;
    Button btnTrans;
    Button btnDet;
    Button btnOrth;
    Button btnInv;

    Button btnAdd;
    Button btnProd;
    Button btnnumProd;
    Button btnMinus;

    Bundle bundle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.functions);

        btnTrace = (Button) findViewById(R.id.trace);
        btnTrans = (Button) findViewById(R.id.transpose);
        btnDet = (Button) findViewById(R.id.determination);
        btnOrth = (Button) findViewById(R.id.orthogonic);
        btnInv = (Button) findViewById(R.id.invert);
        btnAdd = (Button) findViewById(R.id.add);
        btnProd = (Button) findViewById(R.id.product);
        btnnumProd = (Button) findViewById(R.id.numProduct);
        btnMinus = (Button) findViewById(R.id.minus);



    }

    public void buttonOnClick(View view)
    {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("func", view.getId());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
