package com.bkredfox.minhhien.matrixcal_demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by minhhien on 19/03/2016.
 */
public class JsonUtil {
    static String toJson(Matrix matrix) {
        if (matrix == null) return null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rows", matrix.rows);
            jsonObject.put("cols", matrix.cols);
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < matrix.rows; i++) {
                for (int j = 0; j < matrix.cols; j++) {
                    jsonArray.put(matrix.data[i][j]);
                }
            }
            jsonObject.put("data", jsonArray);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Matrix toMatrix(String jsonString) {
        if (jsonString == null) return null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            int cols = jsonObject.getInt("cols");
            int rows = jsonObject.getInt("rows");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            double[][] data = new double[rows][cols];
            int c = 0;
            int r = 0;
            for (int counter = 0; counter < jsonArray.length(); counter++) {
                data[r][c] = jsonArray.getDouble(counter);
                c++;
                if (c == cols) {
                    c = 0;
                    r++;
                }
            }
            Matrix mat = new Matrix(data, rows, cols);
            return mat;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}

