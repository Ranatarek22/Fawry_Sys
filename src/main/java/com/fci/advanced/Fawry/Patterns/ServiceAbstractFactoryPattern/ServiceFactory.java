package com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern;

public interface ServiceFactory {
    MobileService createMobileRechargeService();

    InternetService createInternetPaymentService();

}
