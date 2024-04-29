package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class VodafoneMobileService implements MobileService {
    double costPerUnit = 2.2;
    double cost;
    public VodafoneMobileService() 
    {
    }

    @Override
    public double ServiceCost(double amount) {
        cost=amount * costPerUnit;
        return cost;
    }

    @Override
    public double getServiceCost() {
        return cost;
    }
    public double getCostPerUnit()
    {
        return costPerUnit;
    }

}
