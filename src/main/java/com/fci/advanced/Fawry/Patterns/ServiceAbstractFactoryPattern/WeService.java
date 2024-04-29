package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class WeService implements ServiceFactory {

    @Override
    public MobileService createMobileRechargeService() {

        return new WeMobileService();
    }

    @Override
    public InternetService createInternetPaymentService() {

        return new WeInternetService();
    }
    
}
