package com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern;

public class NGOsService implements DonationFactory {

    @Override
    public DonationService createDonationAmount() {
        
        return new NGOsDonation();
    }
    
}
