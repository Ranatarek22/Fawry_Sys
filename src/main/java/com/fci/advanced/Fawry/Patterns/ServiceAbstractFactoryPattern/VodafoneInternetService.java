package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class VodafoneInternetService implements InternetService {
    double costPerGiga = 2.0;
    double cost;

    public VodafoneInternetService() {


    }

    @Override
    public double ServiceCost(double amount) {
        cost = amount * costPerGiga;
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
