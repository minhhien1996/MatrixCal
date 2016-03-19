package com.bkredfox.minhhien.matrixcal_demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class matrixDisplay extends Activity {

    public Button btnLoad;
    public Button btnSave;
    public Button btnCal;

    public Button btnAddCol;
    public Button btnRmCol;
    public Button btnAddRow;
    public Button btnRmRow;
    public TableLayout matrixContainer;

    public EditText[][] matrix;

    //1 for first input, 2 for second input, 3 for show reseult
    /*matDis(1) -> func         -> matDis(3)
                           v -> matDis(2) -> ^
                           */
    int stage;

    int func;

    Solver solver;

    Matrix mat1, mat2, matRes;



    public int columns;
    public int rows;

    public NumberSolve numberParsingSolver;

    JsonUtil jsonUtil;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.matrix);

        solver = new Solver();
        numberParsingSolver = new NumberSolve();
        matrixContainer = (TableLayout) findViewById(R.id.matrixContainer);
        jsonUtil = new JsonUtil();

        btnRmRow = (Button) findViewById(R.id.btnRmRow);
        btnAddRow = (Button) findViewById(R.id.btnAddRow);
        btnAddCol = (Button) findViewById(R.id.btnAddCol);
        btnRmCol = (Button) findViewById(R.id.btnRmCol);

        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCal = (Button) findViewById(R.id.btnCal);

        setInitValue();



    }

    void saveMatrix(){
        SharedPreferences preferences = getSharedPreferences("my_matrix", MODE_PRIVATE);
        if (preferences !=null){
            SharedPreferences.Editor editor = preferences.edit();
            if (mat1 != null) editor.putString("mat1", jsonUtil.toJson(mat1));
            if (mat2 != null) editor.putString("mat2",jsonUtil.toJson(mat2));
            if (matRes != null) editor.putString("matRes",jsonUtil.toJson(matRes));
            editor.commit();

        }

    }

    void loadMatrix(){
        SharedPreferences preferences = getSharedPreferences("my_matrix", MODE_PRIVATE);
        if (preferences != null)
        {
            mat1 = JsonUtil.toMatrix(preferences.getString("mat1",null));
            mat2 = JsonUtil.toMatrix(preferences.getString("mat2",null));
            matRes = JsonUtil.toMatrix(preferences.getString("matRes",null));
        }

    }

    void setInitValue(){
        columns = 2;
        rows = 2;

        matRes = null;
        mat1 = null;
        mat2 = null;
        stage = 1;

        func = 0;

        generateTable();

        btnCal.setText(getString(R.string.calculate));
    }

    @Override
    public void onResume(){
        super.onResume();
        loadMatrix();
        switch (stage) {
            case 1:{

                generateTable();
                break;
            }
            case 2:{
                generateTable();
                break;
            }
            case 3:{
                btnCal.setText(getString(R.string.start_over));
                break;
            }
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        saveMatrix();
    }

    public void showToast(String s)
    {
        Context context = getApplicationContext();
        CharSequence text = s;
        int duration = Toast.LENGTH_SHORT;

        final Toast toast = Toast.makeText(context, text, duration);
        new CountDownTimer(1000, 1000)
        {
            public void onTick(long millisUntilFinished) {toast.show();}
            public void onFinish() {toast.cancel();}
        }.start();
        toast.show();

    }

    public Matrix buildMatrix(){
        Matrix res = new Matrix(rows,columns);
        for (int i=0; i<rows;i++)
        {
            for (int j=0; j<columns; j++){
                if (numberParsingSolver.SolveNumber(matrix[i][j].getText().toString())){
                    res.data[i][j] = numberParsingSolver.result;
                }
                else
                {
                    return null;
                }
            }
        }
        return res;
    }

    public void generateTable() {
        if (func == R.id.numProduct)
        {
            btnRmRow.setVisibility(View.INVISIBLE);
            btnAddCol.setVisibility(View.INVISIBLE);
            btnAddRow.setVisibility(View.INVISIBLE);
            btnRmCol.setVisibility(View.INVISIBLE);
            columns = 1;
            rows = 1;
        }
        else    {
            btnRmRow.setVisibility(View.VISIBLE);
            btnAddCol.setVisibility(View.VISIBLE);
            btnAddRow.setVisibility(View.VISIBLE);
            btnRmCol.setVisibility(View.VISIBLE);

        }

        matrixContainer.removeAllViews();
        matrix = new EditText[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            TableRow row = new TableRow(this);

            for (int j = 0; j < columns; j++)
            {
                matrix[i][j] = new EditText(this);
                matrix[i][j].setText("0.0");
                matrix[i][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                matrix[i][j].setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                matrix[i][j].setSingleLine(false);
                matrix[i][j].setLines(2);
                row.addView(matrix[i][j]);
            }
            matrixContainer.addView(row,new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }


    }

    public void showResult(Matrix mat){
        matrixContainer.removeAllViews();
        rows = mat.rows;
        columns = mat.cols;
        matrix = new EditText[rows][columns];

        for (int i = 0; i < rows; i++)
        {
            TableRow row = new TableRow(this);

            for (int j = 0; j < columns; j++)
            {
                matrix[i][j] = new EditText(this);
                matrix[i][j].setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                matrix[i][j].setLayoutParams(new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                matrix[i][j].setSingleLine(false);
                matrix[i][j].setLines(2);
                matrix[i][j].setText(Double.toString(mat.data[i][j]));
                row.addView(matrix[i][j]);
            }
            matrixContainer.addView(row,new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
        btnCal.setText(getString(R.string.start_over));
        btnRmRow.setVisibility(View.INVISIBLE);
        btnAddCol.setVisibility(View.INVISIBLE);
        btnAddRow.setVisibility(View.INVISIBLE);
        btnRmCol.setVisibility(View.INVISIBLE);
    }

    public void btnAddCol_clickListener(View v)
    {
        columns++;
        if (columns > 5)
        {
            columns = 5;
            showToast(getString(R.string.max_col));
        }
        generateTable();
    }

    public void btnAddRow_clickListener(View v)
    {
        rows++;
        if (rows > 5)
        {
            rows = 5;
            showToast(getString(R.string.max_row));
        }
        generateTable();
    }

    public void btnRmCol_clickListener(View v)
    {
        columns--;
        if (columns < 2)
        {
            columns = 2;
            showToast(getString(R.string.min_col));
        }
        generateTable();
    }

    public void btnRmRow_clickListener(View v)
    {
        rows--;
        if (rows < 2)
        {
            rows = 2;
            showToast(getString(R.string.min_row));
        }
        generateTable();
    }

    public void backToMenu(View view){

        this.finish();

    }

    public void calculate(View view){
        //Button btn = (Button) findViewById(R.id.btnCal);
        //btn.setBackground(getResources().getDrawable(R.drawable.matrix_bg_button_clicked));
        //btn.setTextColor(Color.parseColor("#ffffff"));
        //int img = R.drawable.calculate2;
        //btn.setCompoundDrawablesWithIntrinsicBounds(0, img, 0, 0);
        switch (stage){
            case 1:{
                mat1 = buildMatrix();
                if (mat1 != null)
                {
                    Intent calculate = new Intent(this,functions.class);
                    saveMatrix();
                    startActivityForResult(calculate, 0);
                }
                else {
                    showToast(getString(R.string.invalid_input));
                }
                break;
            }

            case 2:{
                mat2 = buildMatrix();
                switch (func)
                {
                    case    R.id.add:{
                        stage = 3;
                        matRes = solver.Add(mat1,mat2);
                        showResult(matRes);
                        break;
                    }
                    case    R.id.minus:{
                        stage = 3;
                        matRes = solver.Subtract(mat1,mat2);
                        showResult(matRes);
                        break;
                    }
                    case    R.id.product:{
                        stage = 3;
                        matRes = solver.Multiply(mat1, mat2);
                        showResult(matRes);
                        break;
                    }
                    case    R.id.numProduct:{
                        stage = 3;
                        matRes = solver.Multiply(mat1,mat2.data[0][0]);
                        showResult(matRes);
                        break;
                    }
                }
                break;
            }

            case 3:{

                setInitValue();
                /*
                Intent intent_calculator = new Intent(this,matrixDisplay.class);
                startActivity(intent_calculator);
                this.finish();
                */

                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){

                func = intent.getIntExtra("func", 0);
                loadMatrix();
                switch (func)
                {
                    case    R.id.trace:{
                        stage = 3;
                        matRes = new Matrix(1,1);
                        matRes.data[0][0] = solver.Trace(mat1);
                        showResult(matRes);
                        break;
                    }
                    case    R.id.transpose:{
                        stage = 3;
                        matRes = solver.Transpose(mat1);
                        showResult(matRes);
                        break;
                    }
                    case    R.id.invert:{
                        stage = 3;
                        matRes = solver.Reverse(mat1);
                        showResult(matRes);
                        break;
                    }
                    case    R.id.determination:{
                        stage = 3;
                        matRes = new Matrix(1,1);
                        matRes.data[0][0] = solver.Det(mat1);
                        showResult(matRes);
                        break;
                    }
                    case    R.id.orthogonic:{
                        //for test
                        stage = 3;
                        matRes = mat1;
                        showResult(matRes);
                        break;
                    }
                    case    R.id.add:{
                        stage = 2;
                        break;
                    }
                    case    R.id.minus:{
                        stage = 2;
                        break;
                    }
                    case    R.id.product:{
                        stage = 2;
                        break;
                    }
                    case    R.id.numProduct:{
                        stage = 2;
                        break;
                    }
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                showToast(getString(R.string.action_canceled));
            }
        }


    }




    public void load(View view){
        Intent load = new Intent(this,storage.class);
        startActivity(load);
    }

    public void save(View view){
        Intent save = new Intent(this,storage.class);
        startActivity(save);
    }

}
