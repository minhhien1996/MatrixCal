package com.bkredfox.minhhien.matrixcal_demo;

/**
 * Created by minhhien on 05/04/2016.
 */
public class Polynomial {
    // the coefficients are stored in a 'double' array, and the degree is an int
    public double[] coef;
    public int deg;

    // we can initialize a polynomial only knowing the degree
    public Polynomial(int deg) {
        this.deg = deg;
        this.coef = new double[deg + 1];
    }

    // or supplying all coefficients
    public Polynomial(double[] coef) {
        this.coef = coef;
        deg = coef.length - 1;
    }

    // for absolute value
    public static double abs(double x) {
        if (x >= 0) return x;
        else return -x;
    }

    // solely for rounding, happens when the value is too close to int
    public static double beautify(double x) {
        if (abs(x - Math.floor(x)) < 1E-2) return (double) (Math.floor(x));
        if (abs(x - Math.floor(x) - 1) < 1E-2) return (double) (Math.floor(x) + 1);
        return x;
    }

    // redefine equality for the sake of errors
    public static boolean equals(double x, double y) {
        if (abs(x - y) < 1E-2) return true;
        return false;
    }

    // to evaluate the polynomial bearing it with the value x
    public double subs(double x) {
        double output = 0;
        double num = 1;
        for (int i = 0; i <= this.deg; i++) {
            output += this.coef[i] * num;
            num *= x;
        }
        return output;
    }

    // this version is supplied with an int, since int opration is faster
    public double subs(int x) {
        double output = 0;
        int num = 1;
        for (int i = 0; i <= this.deg; i++) {
            output += this.coef[i] * num;
            num *= x;
        }
        return output;
    }

    // this will find and return one root of the polynomial within the segment [lower,upper]
    public double RootInInterval(double lower, double upper, int upperIsPositive) {
        if (upper - lower < 1E-4) return (upper + lower) / 2;
        double subu, subl;
        if (upperIsPositive == 2) {
            subu = this.subs(upper);
            subl = this.subs(lower);
            if (equals(subu, 0)) return upper;
            if (equals(subl, 0)) return lower;
//			if (subu * subl > 0) return 0;
        } else if (upperIsPositive == 1) {
            subu = 1;
            subl = -1;
        } else {
            subu = -1;
            subl = 1;
        }
        double av = (upper + lower) / 2;
        double suba = this.subs(av);
        if (equals(suba, 0)) return av;
        if (suba * subu < 0) return this.RootInInterval(av, upper, (subu > 0) ? 1 : 0);
        else return this.RootInInterval(lower, av, (suba > 0) ? 1 : 0);
    }

    // this one is a self-recursive method that tries to find all real roots for the polynomial
    public Roots FindRoots() {
        if (this.deg == 1) {
            Roots output = new Roots(this, 1);
            output.val[0] = -this.coef[0] / this.coef[1];
            output.count = 1;
            return output;
        }
        if (this.deg == 2) {
            double a = this.coef[2], b = this.coef[1], c = this.coef[0];
            double delta = b * b - 4 * a * c;
            if (delta < 0)
                return null;
            else if (delta == 0) {
                Roots output = new Roots(this, 1);
                output.val[0] = -b / 2 / a;
                output.mul[0] = 2;
                output.count = 1;
                return output;
            } else {
                Roots output = new Roots(this, 2);
                delta = Math.sqrt(delta);
                output.count = 2;
                output.val[0] = (-b - delta) / 2 / a;
                output.val[1] = (-b + delta) / 2 / a;
                if (a < 0) {
                    double temp = output.val[0];
                    output.val[0] = output.val[1];
                    output.val[1] = temp;
                }
                return output;
            }
        }

        Polynomial dif = new Polynomial(this.deg - 1);
        for (int i = 1; i <= this.deg; i++)
            dif.coef[i - 1] = i * this.coef[i];
        Roots difRoots = dif.FindRoots();

        double currRoot;
        Roots output = new Roots(this, difRoots.count + 1);
        int i = 0;

        double subl = this.subs(-1E6), subu = this.subs(difRoots.val[0]);
        if (subu * subl <= 0) {
            currRoot = this.RootInInterval(-1E6, difRoots.val[0], 2);
            output.val[0] = beautify(currRoot);
            if (currRoot == difRoots.val[0]) {
                output.mul[0] = difRoots.mul[0] + 1;
                output.count -= difRoots.mul[0];
            } else i--;
            i++;
        } else output.count--;
        i++;
        while (i < difRoots.count) {
            subl = this.subs(difRoots.val[i - 1]);
            subu = this.subs(difRoots.val[i]);
            if (subu * subl > 0) {
                i++;
                output.count--;
            } else {
                currRoot = this.RootInInterval(difRoots.val[i - 1], difRoots.val[i], 2);
                output.val[i] = beautify(currRoot);
                if (currRoot == difRoots.val[i - 1]) {
                    output.mul[i] = difRoots.mul[i - 1] + 1;
                    output.count -= difRoots.mul[i - 1];
                } else if (currRoot == difRoots.val[i]) {
                    output.mul[i] = difRoots.mul[i] + 1;
                    output.count -= difRoots.mul[i];
                } else i--;
                i += 2;
            }
        }
        int n = difRoots.count;
        if (i > n) return output;
        subl = this.subs(difRoots.val[n - 1]);
        subu = this.subs(1E6);
        if (subu * subl <= 0) {
            currRoot = this.RootInInterval(difRoots.val[n - 1], 1E6, 2);
            output.val[n] = beautify(currRoot);
            i += 1;
        } else output.count--;

        return output;

    }
};
