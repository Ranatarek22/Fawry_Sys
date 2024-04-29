package com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern;

import java.util.ArrayList;
import java.util.Scanner;
public class Admin implements Subject {
    private Scanner in = new Scanner(System.in);
    private int ask;
    private ArrayList <Observer> observers;
    private String newService;
    private static ArrayList<String> newServices=new ArrayList<String>();
    public static ArrayList <String> userPaymentTransactionsName = new ArrayList<String>();
    public static ArrayList <Double> userPaymentTransactionsCost = new ArrayList<Double>();
    public static ArrayList <Double> userAddToWalletTransactionsAmount = new ArrayList<Double>();
    public static ArrayList <String> userRefundTransactionsName = new ArrayList<String>();
    public static ArrayList <Double> userRefundTransactionsAmount = new ArrayList<Double>();
    

    public Admin()
    {
        this.observers=new ArrayList<Observer>();
        newService="";
    }
    public void addService(String s) {
        this.newService=s;
        this.newServices.add(s);
        this.notifyObserver();
    }
    public ArrayList<String> getarray()
    {
        return this.newServices;
    }
    public Boolean acceptRefund(String acceptance)
    {
        if(acceptance.toLowerCase().equals("yes"))
        {
            return  true;
        }
        else
        {
            return false;
        }
    }
    
    @Override
    public String getNewService() {
        return "New Service " + newService +" is added successfully!";
    }
    @Override
    public void registerObserver(Observer o) 
    {
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer o) 
    {
        observers.remove(o);
    }
    @Override
    public void notifyObserver() {
        for(Observer obj:observers){
            obj.Update(this);
        }
        
    }
}

