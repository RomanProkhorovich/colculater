package com.example.colculater.Model;

public  class Operation {

    public static double executeUnarOperation(double arg, String operator){
        switch (operator){
            case ("√"): {
                if (arg < 0)
                    throw new NegativeSqrtArgException();
                return Math.sqrt(arg);
            }
        }
        throw new ArithmeticException();
    }

    public  static double executeBinaryOperation(double arg1,double arg2,String operator){
        switch (operator) {
            case ("+") -> {
                return arg1 + arg2;
            }
            case ("-") -> {
                return arg1 - arg2;
            }
            case ("*") -> {
                return arg1 * arg2;
            }
            case ("/") -> {
                if (arg2 == 0)
                    throw new ZeroDevideException();
                return arg1 / arg2;
            }
            case ("^") -> {
                if (Math.abs(arg2) < 1 && (arg1 < 0))
                    throw new NegativeSqrtArgException();
                return Math.pow(arg1, arg2);
            }
        }
        throw new ArithmeticException();
    }
}
