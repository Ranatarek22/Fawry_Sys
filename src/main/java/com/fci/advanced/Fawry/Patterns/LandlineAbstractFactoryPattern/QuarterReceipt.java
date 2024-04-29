package com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern;

public class QuarterReceipt implements LandlineService {
    private final static double cost = 600;

    public QuarterReceipt() {
        // System.out.println("you have to pay " + serviceCost() + " L.E");
    }

    @Override
    public double serviceCost() 
    {
        return cost;
    }

}
