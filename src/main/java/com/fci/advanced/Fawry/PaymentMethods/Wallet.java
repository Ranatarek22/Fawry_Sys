package com.fci.advanced.Fawry.PaymentMethods;

public class Wallet implements Payment{
    double walletBalance;

    public Wallet()
    {
        walletBalance= 1000;
    }

    public Wallet(double walletBalance) 
    {
        this.walletBalance = walletBalance;
    }

    @Override
    public void pay(double num) 
    {
        walletBalance-=num;

    }
    public void addingToWallet(CreditCard credit,double num){
        credit.pay(num);
        walletBalance+=num;
    }
    public void setWalletBalance(double w)
    {
        this.walletBalance=w;
    }
    public double getWalletBalance()
    {
        return walletBalance;
    }
    public String DisplayWalletBalance()
    {
        return "You have " + walletBalance + " pound in your walletBalance";
    }

    public void refund(double amount) {
        walletBalance+=amount;
        
    }
        
    public boolean checkCreditCardBalance(CreditCard credit,double amount)
    {
        if(credit.getCreditBalance()<amount)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean checkWalletBalance(double amount)
    {
        if(amount>walletBalance)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
