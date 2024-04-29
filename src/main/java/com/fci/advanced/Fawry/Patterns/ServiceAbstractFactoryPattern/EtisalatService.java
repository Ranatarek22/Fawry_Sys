package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class EtisalatService implements ServiceFactory {

    @Override
    public MobileService createMobileRechargeService() {
    
        return new EtisalatMobileService() ;
    }

    @Override
    public InternetService createInternetPaymentService() {
        return new EtisalatInternetService();
    }
    
}
