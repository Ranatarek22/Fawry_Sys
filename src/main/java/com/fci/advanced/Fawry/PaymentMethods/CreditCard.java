package com.fci.advanced.Fawry.PaymentMethods;
public class CreditCard implements Payment{
    double creditBalance;
    
    public CreditCard() // Default constructor
    {
        creditBalance=2000;
    }
    public CreditCard(double creditBalance)  // parameterized constructor
    {
        this.creditBalance = creditBalance;
    }

    @Override
    public void pay(double num)  // the main payment function
    {
        creditBalance-=num;
        
    }
    public void setCreditBalance(double c)
    {
        this.creditBalance=c;
    }
    public double getCreditBalance()
    {
        return creditBalance;
    }

    public String DisplayCreditBalance()
    {
        return "You have " + creditBalance + " pound in your CreditBalance";
    }
    public boolean checkCreditCardBalance(double amount)
    {
        if(this.creditBalance<amount)
        {
            return false;
        }
        else{
            return true;
        }
    }


}