package com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.CustomerModel;

import com.fci.advanced.Fawry.Functions.Functions;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.Admin;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.Observer;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.Subject;
import com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern.DonationFactory;
import com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern.LandlineFactory;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.ServiceFactory;

import java.util.ArrayList;
public class Customer extends Observer {

    public ServiceFactory Service;
    public LandlineFactory Landline;
    public DonationFactory Donation;
    public static ArrayList<String> arr =new ArrayList<String>();
    private String userName;
    private String email;
    private String password;
    
    public Customer(){
    }
    public  Customer(String userName , String email , String password)
    {
        this.userName=userName;
        this.email=email;
        this.password=password;
    }
    public Customer(Subject s)
    { 
        this.sub=s;
        this.sub.registerObserver(this);
    }

    public void setUserName(String n) {
        this.userName = n;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String n) {
        this.email = n;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String n) {
        this.password = n;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public ArrayList<String> Update(Admin a)
    {
        arr=a.getarray();
        
        if(arr.size()==0){
            return null;
        }
        else
        {
            return  arr;
        }
        
    }
    public double getMobileOffers(Functions o)
    {
        return o.getMobileDiscount();
        
    }
    public double getInternetOffers(Functions o)
    {
        return o.getInternetDiscount();
        
    }
    public double getLandlineOffers(Functions o)
    {
        return o.getLandlineDiscount();  
    }
    public double getOverallOffer(Functions o)
    {
        return o.getOverallDiscount();
    }

}
