package com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern;

public class NGOsDonation implements DonationService {
    public NGOsDonation()
    {


    }

    @Override
    public Double donationCost(double amount) {
        
        return amount;
    }
    
}
