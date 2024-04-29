package com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern;

public class SchoolsService implements DonationFactory {

    @Override
    public DonationService createDonationAmount() {

        return new SchoolDonation();
    }

}
