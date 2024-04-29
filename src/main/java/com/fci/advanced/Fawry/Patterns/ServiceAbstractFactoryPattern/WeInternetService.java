package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class WeInternetService implements InternetService {
    double costPerGiga = 2.0;
    double cost;

    public WeInternetService() {


    }

    @Override
    public double ServiceCost(double amount) {
        cost=amount * costPerGiga;
        return cost;
    }

    @Override
    public double getServiceCost() {
        return cost;
    }
    public double getCostPerGiga()
    {
        return costPerGiga;
    }

}
