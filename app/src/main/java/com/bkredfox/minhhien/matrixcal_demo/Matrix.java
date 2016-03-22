package com.bkredfox.minhhien.matrixcal_demo;

/**
 * Created by minhhien on 13/03/2016.
 */
/*
import java.io.*;
import java.lang.*;
*/

import java.io.Serializable;

public class Matrix implements Serializable {
    double[][] data;
    int rows = 0, cols = 0;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public Matrix(double[][] data, int rows, int cols) {
        this.data = data;
        this.rows = rows;
        this.cols = cols;
    }

    public boolean DimMatch(Matrix other) {
        if (this.rows == other.rows && this.cols == other.cols) return true;
        return false;

    }

    public boolean DimMatchMul(Matrix other) {
        if (this.cols == other.rows) return true;
        return false;
    }

    public Matrix Copy(int rstart, int cstart, int rspan, int cspan) {
        Matrix output = new Matrix(rspan, cspan);
        for (int i = rstart; i < rstart + rspan; i++)
            for (int j = cstart; j < cstart + cspan; j++)
                output.data[i - rstart][j - cstart] = this.data[i % this.rows][j % this.cols];
        return output;
    }
}