package com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern;

public class CancerHospitalService implements DonationFactory {

    @Override
    public DonationService createDonationAmount() {
        
        return new CancerHospitalDonation();
    }
    
}
