package com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern;

import com.fci.advanced.Fawry.PaymentMethods.CreditCard;
import com.fci.advanced.Fawry.PaymentMethods.Wallet;

import java.util.ArrayList;

public abstract class Observer {
    protected Subject sub;
    public CreditCard creditCard;
    public Wallet wallet;
    public static ArrayList <String> serviceNameArray =new ArrayList<String>();
    public static ArrayList <Double> serviceCostArray= new ArrayList<Double>();
    public abstract ArrayList<String> Update(Admin a);
    
}
