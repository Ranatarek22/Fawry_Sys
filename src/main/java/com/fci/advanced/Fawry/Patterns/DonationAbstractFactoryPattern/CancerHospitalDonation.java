package com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern;

public class CancerHospitalDonation implements DonationService {
    public CancerHospitalDonation()
    {
    }

    @Override
    public Double donationCost(double amount) {
    
        return amount;
    }
    
}
