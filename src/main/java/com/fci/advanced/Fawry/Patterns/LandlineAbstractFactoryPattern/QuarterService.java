package com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern;

public class QuarterService implements LandlineFactory {

    @Override
    public LandlineService createReceipt() {
        
        return new QuarterReceipt();
    }
    
}
