package com.bkredfox.minhhien.matrixcal_demo;

/**
 * Created by minhhien on 13/03/2016.
 */

public class Solver {
    public Matrix Add(Matrix m1, Matrix m2) {
        Matrix output = new Matrix(m1.rows, m1.cols);
        if (m1.DimMatch(m2)) {
            for (int i = 0; i < m1.rows; i++)
                for (int j = 0; j < m1.cols; j++) {
                    output.data[i][j] = m1.data[i][j] + m2.data[i][j];
                }
        } else {
            //System.out.print("Size Mismatch !");
            //TODO: message for size mismatch
            return null;
        }

        return output;
    }

    public Matrix Subtract(Matrix m1, Matrix m2) {
        Matrix output = new Matrix(m1.rows, m1.cols);
        if (m1.DimMatch(m2)) {
            for (int i = 0; i < m1.rows; i++)
                for (int j = 0; j < m1.cols; j++) {
                    output.data[i][j] = m1.data[i][j] - m2.data[i][j];
                }
        } else {
            //System.out.print("Size Mismatch !");
            //TODO: message for size mismatch
            return null;
        }

        return output;
    }

    public Matrix Multiply(Matrix m1, Matrix m2) {
        if (m1.DimMatchMul(m2)) {
            Matrix output = new Matrix(m1.rows, m2.cols);
            int len = m1.cols;
            for (int i = 0; i < m1.rows; i++)
                for (int j = 0; j < m1.cols; j++) {
                    int sum = 0;
                    for (int k = 0; k < len; k++)
                        sum += m1.data[i][k] * m2.data[k][j];
                    output.data[i][j] = sum;
                }
            return output;
        } else {

            return null;
        }
    }

    public Matrix Multiply(Matrix m, double num) {
        double[][] result = new double[m.rows][m.cols];
        for (int i = 0; i < m.rows; i++)
            for (int j = 0; j < m.cols; j++)
                result[i][j] = m.data[i][j] * num;
        Matrix output = new Matrix(result, m.rows, m.cols);
        return output;
    }

    public Matrix Transpose(Matrix m) {
        double[][] result = new double[m.cols][m.rows];
        for (int i = 0; i < m.rows; i++)
            for (int j = 0; j < m.cols; j++)
                result[j][i] = m.data[i][j];
        Matrix output = new Matrix(result, m.cols, m.rows);
        return output;
    }

    public double Trace(Matrix m) {
        double output = 0;
        if (m.rows == m.cols)
            for (int i = 0; i < m.rows; i++)
                output += m.data[i][i];
        else return 0;
        return output;
    }

    public double Det(Matrix m) {
        if (m.rows != m.cols) return 0;
        if (m.rows == 1) return m.data[0][0];
        //int sign = 1;
        double output = 0;
        if (m.rows == 2) output = m.data[0][0] * m.data[1][1] - m.data[0][1] * m.data[1][0];
        else for (int k = 0; k < m.rows; k++) {
            //sign = (k+1)%2 == 0? 1: -1;
            output += Det(m.Copy(1, (k + 1) % m.rows, m.rows - 1, m.rows - 1)) * m.data[0][k];
        }
        return output;
    }

    public Matrix Reverse(Matrix m) {
        Matrix output = new Matrix(m.cols, m.rows);
        for (int i = 0; i < m.rows; i++)
            for (int j = 0; j < m.cols; j++)
                output.data[j][i] = Det(m.Copy((i + 1) % m.rows, (j + 1) % m.cols, m.rows - 1, m.cols - 1));
        double d = Det(m);
        if (d != 0)
            output = Multiply(output, 1 / d);
        else {
            //TODO
        }
        return output;
    }

}