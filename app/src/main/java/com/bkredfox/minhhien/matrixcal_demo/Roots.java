package com.bkredfox.minhhien.matrixcal_demo;

/**
 * Created by minhhien on 05/04/2016.
 */
public class Roots {
    Polynomial po;
    double[] val;
    int[] mul;
    int count;

    public Roots(Polynomial po, int numOfRoots) {
        this.po = po;
        val = new double[numOfRoots];
        mul = new int[numOfRoots];
        for (int i = 0; i < numOfRoots; i++)
            mul[i] = 1;
        count = po.deg;
    }
}