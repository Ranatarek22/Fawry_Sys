package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class EtisalatInternetService implements InternetService {
    double costPerGiga = 2.0;
    double cost;
    public EtisalatInternetService() {


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
