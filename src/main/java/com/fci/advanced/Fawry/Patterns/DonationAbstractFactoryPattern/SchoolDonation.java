package com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern;

public class SchoolDonation implements DonationService {
    public SchoolDonation()
    {


    }

    @Override
    public Double donationCost(double amount) {
    
        return amount;
    }
    
}
