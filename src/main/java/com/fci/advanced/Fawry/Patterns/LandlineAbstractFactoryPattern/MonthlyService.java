package com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern;

public class MonthlyService implements LandlineFactory {

    @Override
    public LandlineService createReceipt() {
    
        return new MonthlyReceipt();
    }
    
}
