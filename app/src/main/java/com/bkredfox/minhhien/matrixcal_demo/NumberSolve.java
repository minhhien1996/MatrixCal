package com.bkredfox.minhhien.matrixcal_demo;

/**
 * Created by minhhien on 13/03/2016.
 */

/*
import java.io.*;
*/
public class NumberSolve {
    static double result; //Nhan ket qua cua SolveNumber
    public static boolean SolveNumber(String line){
        boolean  isNumber = false, Dot = false;
        int mark = 0, Minus = 0;
        double	number = 0;
        String temp ;
        for(int i = 0; i < line.length(); i++){
            if(line.charAt(i) == '-')
                if(isNumber)
                    return false;
                else Minus++;
            else if(line.charAt(i) >= '0' && line.charAt(i) <='9'){
                if(!isNumber) {
                    isNumber = true;
                    mark = i;
                }
            }
            else if(line.charAt(i) == '.'){
                if(!Dot)
                    Dot = true;
                else return false;
            }else return false;
        }
        temp = line.substring(mark);
        number = Double.parseDouble(temp);
        if(Minus % 2 == 1)
            number = -number;
        result = number;
        return true;
    }

    /*
    public static void main (String[] args){
        String number = "--23.4.245";
        if(!SolveNumber(number))
            System.out.println("Error Number");
        else System.out.println("Number: " + result);

    }
    */
}
