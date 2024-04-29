package com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern;

public class MonthlyReceipt implements LandlineService {
    private final static double cost = 200;

    public MonthlyReceipt() {
        
        // System.out.println("you have to pay " + serviceCost() + " L.E");
    }

    @Override
    public double serviceCost() {

        return cost;
    }



}
