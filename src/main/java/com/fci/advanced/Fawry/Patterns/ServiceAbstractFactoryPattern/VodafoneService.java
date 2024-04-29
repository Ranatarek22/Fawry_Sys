package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public class VodafoneService implements ServiceFactory {

    @Override
    public MobileService createMobileRechargeService() {

        return new VodafoneMobileService();
    }

    @Override
    public InternetService createInternetPaymentService() {

        return new VodafoneInternetService();
    }

}
