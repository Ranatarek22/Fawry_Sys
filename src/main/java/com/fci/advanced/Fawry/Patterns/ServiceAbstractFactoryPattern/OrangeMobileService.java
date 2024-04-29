package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class OrangeMobileService implements MobileService {
    double costPerUnit = 2.0;
    double cost;
    public OrangeMobileService() {


    }

    @Override
    public double ServiceCost(double amount) {
        cost =amount* costPerUnit;
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
