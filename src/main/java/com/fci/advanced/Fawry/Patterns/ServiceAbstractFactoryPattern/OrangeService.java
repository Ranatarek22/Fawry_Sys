package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class OrangeService implements ServiceFactory {

    @Override
    public MobileService createMobileRechargeService() {

        return new OrangeMobileService();
    }

    @Override
    public InternetService createInternetPaymentService() {
        
        return new OrangeInternetService();
    }
    
}
