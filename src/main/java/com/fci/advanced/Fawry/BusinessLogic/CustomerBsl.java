package com.fci.advanced.Fawry.BusinessLogic;

import com.fci.advanced.Fawry.PaymentMethods.CreditCard;
import com.fci.advanced.Fawry.Functions.Search;
import com.fci.advanced.Fawry.PaymentMethods.Wallet;
import com.fci.advanced.Fawry.Functions.Functions;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.Admin;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.CustomerData.Customer;
import com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern.CancerHospitalService;
import com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern.DonationService;
import com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern.NGOsService;
import com.fci.advanced.Fawry.Patterns.DonationAbstractFactoryPattern.SchoolsService;
import com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern.*;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;

@Service
public class CustomerBsl
{
    private static ArrayList<Customer> customersData=new ArrayList<Customer>();; // customerDB
    public CustomerBsl(){
    }
    Scanner in=new Scanner(System.in);
    double ServCost,AftDis,Landlinecost,DonationCost,refundedAmount;

    int numOfService = 0;

    public String add(Customer customer) {
        for (int i=0;i<customersData.size();i++)
        {
            if(customer.getEmail().equals(customersData.get(i).getEmail()))
            {
                return "This Email is already Exist Can't be added";
            }
            else if(customer.getUserName().equals(customersData.get(i).getUserName()))
            {
                return "This UserName is already Exist Can't be added";
            }
        }
            customer.wallet=new Wallet();
            customer.creditCard=new CreditCard();
            customersData.add(customer);
            return "Added Successfully";
  }
  public  Customer getCustomer(String Email, String password)
  {
      for (Customer c:customersData)
      {
          if(c.getEmail().equals(Email )&& c.getPassword().equals(password))
          {
              return  c;
          }
      }

       return null;
  }


    public String mobileServiceChoice(String Email ,String password,String serviceProvider,double amount,String paymentForm, Functions function, Admin admin)
    {
        Customer customerObj=getCustomer(Email,password);
    if(serviceProvider.toLowerCase().equals("vodafone"))
    {
        MobileService mobileV;
        customerObj.Service = new VodafoneService();
        mobileV = customerObj.Service.createMobileRechargeService();
        ServCost = mobileV.ServiceCost(amount);
        if(function.isAviOffer())
        {
                AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                        if(paymentForm.toLowerCase().equals("creditcard"))
                        {
                            if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                            {
                                customerObj.creditCard.pay(AftDis);

                                customerObj.serviceNameArray.add("Vodafone Mobile Service");
                                customerObj.serviceCostArray.add(AftDis);
                                admin.userPaymentTransactionsName.add("Vodafone Mobile Service");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return  "Thanks for using Vodafone Mobile Service\n "+customerObj.creditCard.DisplayCreditBalance();
                            }
                            else{
                                return "There's no enough money in Wallet \n Service payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("wallet"))
                        {
                            if(customerObj.wallet.checkWalletBalance(AftDis))
                            {
                                customerObj.wallet.pay(AftDis);

                                customerObj.serviceNameArray.add("Vodafone Mobile Service");
                                customerObj.serviceCostArray.add(AftDis);
                                admin.userPaymentTransactionsName.add("Vodafone Mobile Service");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return "Thanks for using Vodafone Mobile Service\n "+customerObj.wallet.DisplayWalletBalance();
                            }
                            else
                            {
                                return "There's no enough money in Wallet \n Service payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("cash"))
                        {
                            admin.userPaymentTransactionsName.add("Vodafone Mobile Service (Cash on delivery)");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            function.setAviOffer(false);
                            return "The payment is done successfully by cash on delivery\nThanks for using Vodafone Mobile Service ";
                        }
            }
            else if(function.isAviSMOffer())
            {
                if(function.isSpecMob())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getMobileOffers(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);
                                    customerObj.serviceNameArray.add("Vodafone Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Vodafone Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return "Thanks for using Vodafone Mobile Service \n"+customerObj.creditCard.DisplayCreditBalance();
                                }
                                else{

                                    return "There's no enough money in CreditCard\nService payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);
                                    customerObj.serviceNameArray.add("Vodafone Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Vodafone Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return customerObj.wallet.DisplayWalletBalance()+"\nThanks for using Vodafone Mobile Service";
                                }
                                else
                                {
                                    return  "There's no enough money in Wallet\n Service payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("Vodafone Mobile Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                return "The payment is done successfully by cash on delivery\nThanks for using Vodafone Mobile Service";
                            }
                        }
                    }
                    else
                    {
                        if(paymentForm.toLowerCase().equals("creditcard"))
                        {
                            if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                            {
                                customerObj.creditCard.pay(ServCost);

                                customerObj.serviceNameArray.add("Vodafone Mobile Service");
                                customerObj.serviceCostArray.add(ServCost);
                                admin.userPaymentTransactionsName.add("Vodafone Mobile Service");
                                admin.userPaymentTransactionsCost.add(ServCost);
                                return "Thanks for using Vodafone Mobile Service\n"+customerObj.creditCard.DisplayCreditBalance();
                            }
                            else{
                                return "There's no enough money in CreditCard\nService payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("wallet"))
                        {
                            if(customerObj.wallet.checkWalletBalance(ServCost))
                            {
                                customerObj.wallet.pay(ServCost);
                                customerObj.serviceNameArray.add("Vodafone Mobile Service");
                                customerObj.serviceCostArray.add(ServCost);
                                admin.userPaymentTransactionsName.add("Vodafone Mobile Service");
                                admin.userPaymentTransactionsCost.add(ServCost);
                                return "Thanks for using Vodafone Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();
                            }
                            else
                            {
                                return "There's no enough money in Wallet\nService payment unsuccessful";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("cash"))
                        {
                            admin.userPaymentTransactionsName.add("Vodafone Mobile Service (Cash on delivery)");
                            admin.userPaymentTransactionsCost.add(ServCost);
                            return "The payment is done successfully by cash on delivery\nThanks for using Vodafone Mobile Service ";
                        }
                    }
                }
        else if(serviceProvider.toLowerCase().equals("etisalat"))
        {
            MobileService mobileE = new EtisalatMobileService();
            customerObj.Service = new EtisalatService();
            mobileE = customerObj.Service.createMobileRechargeService();
            ServCost = mobileE.ServiceCost(amount);
            if(function.isAviOffer())
            {
                    AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);

                                    customerObj.serviceNameArray.add("Etisalat Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Etisalat Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "Thanks for using Etisalat Mobile Service \n"+customerObj.creditCard.DisplayCreditBalance();
                                }
                                else
                                {
                                    return "There's no enough money in CreditCard\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);
                                    customerObj.serviceNameArray.add("Etisalat Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Etisalat Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "Thanks for using Etisalat Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();
                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("Etisalat Mobile Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return "The payment is done successfully by cash on delivery\nThanks for using Etisalat Mobile Service";
                            }

            }
            else if(function.isAviSMOffer())
            {
                if(function.isSpecMob())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getMobileOffers(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);

                                    customerObj.serviceNameArray.add("Etisalat Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Etisalat Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return "Thanks for using Etisalat Mobile Service\n"+customerObj.creditCard.DisplayCreditBalance();

                                }
                                else
                                {
                                    return "There's no enough money in CreditCard\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);

                                    customerObj.serviceNameArray.add("Etisalat Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Etisalat Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return "Thanks for using Etisalat Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();
                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("Etisalat Mobile Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                return "The payment is done successfully by cash on delivery\nThanks for using Etisalat Mobile Service";
                            }
                }
            }
            else
        {
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                    {
                        customerObj.creditCard.pay(ServCost);

                        customerObj.serviceNameArray.add("Etisalat Mobile Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Etisalat Mobile Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Etisalat Mobile Service\n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else
                    {
                        return "There's no enough money in CreditCard\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(ServCost))
                    {
                        customerObj.wallet.pay(ServCost);

                        customerObj.serviceNameArray.add("Etisalat Mobile Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Etisalat Mobile Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Etisalat Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("Etisalat Mobile Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(ServCost);
                    return "The payment is done successfully by cash on delivery\nThanks for using Etisalat Mobile Service ";

                }
            }
        }
        else if(serviceProvider.toLowerCase().equals("we"))
        {
            MobileService mobileW = new WeMobileService();
            customerObj.Service = new WeService();
            mobileW = customerObj.Service.createMobileRechargeService();
            ServCost = mobileW.ServiceCost(amount);
            if(function.isAviOffer())
            {
                    AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);

                                    customerObj.serviceNameArray.add("We Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("We Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "Thanks for using We Mobile Service \n"+customerObj.creditCard.DisplayCreditBalance();
                                }
                                else
                                {
                                    return "There's no enough money in CreditCard\nService payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);

                                    customerObj.serviceNameArray.add("We Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("We Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "Thanks for using We Mobile Service \n"+customerObj.wallet.DisplayWalletBalance();
                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("We Mobile Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return "The payment is done successfully by cash on delivery\nThanks for using We Mobile Service ";
                            }
            }
            else if(function.isAviSMOffer())
            {
                if(function.isSpecMob())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getMobileOffers(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);

                                    customerObj.serviceNameArray.add("We Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("We Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return "Thanks for using We Mobile Service"+customerObj.creditCard.DisplayCreditBalance();

                                }
                                else
                                {
                                    return "There's no enough money in CreditCard\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);

                                    customerObj.serviceNameArray.add("We Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("We Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return  "Thanks for using We Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();

                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("We Mobile Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                return "The payment is done successfully by cash on delivery\nThanks for using We Mobile Service ";
                            }
                }
            }
            else
            {
                        if(paymentForm.toLowerCase().equals("creditcard"))
                        {
                            if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                            {
                                customerObj.creditCard.pay(ServCost);

                                customerObj.serviceNameArray.add("We Mobile Service");
                                customerObj.serviceCostArray.add(ServCost);
                                admin.userPaymentTransactionsName.add("We Mobile Service");
                                admin.userPaymentTransactionsCost.add(ServCost);
                                return "Thanks for using We Mobile Service \n"+customerObj.creditCard.DisplayCreditBalance();
                            }
                            else
                            {
                                return "There's no enough money in CreditCard\nService payment unsuccessful";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("wallet"))
                        {
                            if(customerObj.wallet.checkWalletBalance(ServCost))
                            {
                                customerObj.wallet.pay(ServCost);
                                customerObj.serviceNameArray.add("We Mobile Service");
                                customerObj.serviceCostArray.add(ServCost);
                                admin.userPaymentTransactionsName.add("We Mobile Service");
                                admin.userPaymentTransactionsCost.add(ServCost);
                                return "Thanks for using We Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();
                            }
                            else
                            {
                                return "There's no enough money in Wallet\nService payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("cash"))
                        {
                            admin.userPaymentTransactionsName.add("We Mobile Service (Cash on delivery)");
                            admin.userPaymentTransactionsCost.add(ServCost);
                            return "The payment is done successfully by cash on delivery\nThanks for using We Mobile Service";
                        }
            }
        }
        else if(serviceProvider.toLowerCase().equals("orange"))
        {
            MobileService mobileO = new OrangeMobileService();
            customerObj.Service = new OrangeService();
            mobileO = customerObj.Service.createMobileRechargeService();
            ServCost = mobileO.ServiceCost(amount);
            if(function.isAviOffer())
            {
                    AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);

                                    customerObj.serviceNameArray.add("Orange Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Orange Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "Thanks for using Orange Mobile Service\n"+customerObj.creditCard.DisplayCreditBalance();
                                }
                                else{
                                    return "There's no enough money in CreditCard\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);

                                    customerObj.serviceNameArray.add("Orange Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Orange Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "Thanks for using Orange Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();
                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nService payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("Orange Mobile Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return "The payment is done successfully by cash on delivery\nThanks for using Orange Mobile Service ";
                            }



            }
            else if(function.isAviSMOffer())
            {
                if(function.isSpecMob())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getMobileOffers(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);

                                    customerObj.serviceNameArray.add("Orange Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Orange Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return "Thanks for using Orange Mobile Service \n"+customerObj.creditCard.DisplayCreditBalance();

                                }
                                else{
                                    return "There's no enough money in CreditCard\nService payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);

                                    customerObj.serviceNameArray.add("Orange Mobile Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Orange Mobile Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return  "Thanks for using Orange Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();

                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nService payment unsuccessful";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("Orange Mobile Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                return "The payment is done successfully by cash on delivery\nThanks for using Orange Mobile Service ";
                            }
                }
            }
            else
            {
                        if(paymentForm.toLowerCase().equals("creditcard"))
                        {
                            if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                            {
                                customerObj.creditCard.pay(ServCost);

                                customerObj.serviceNameArray.add("Orange Mobile Service");
                                customerObj.serviceCostArray.add(ServCost);
                                admin.userPaymentTransactionsName.add("Orange Mobile Service");
                                admin.userPaymentTransactionsCost.add(ServCost);
                                return "Thanks for using Orange Mobile Service\n "+customerObj.creditCard.DisplayCreditBalance();
                            }
                            else{
                                return "There's no enough money in CreditCard\nService payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("wallet"))
                        {
                            if(customerObj.wallet.checkWalletBalance(ServCost))
                            {
                                customerObj.wallet.pay(ServCost);
                                customerObj.serviceNameArray.add("Orange Mobile Service");
                                customerObj.serviceCostArray.add(ServCost);
                                admin.userPaymentTransactionsName.add("Orange Mobile Service");
                                admin.userPaymentTransactionsCost.add(ServCost);
                                return "Thanks for using Orange Mobile Service\n"+customerObj.wallet.DisplayWalletBalance();
                            }
                            else
                            {
                                return "There's no enough money in Wallet\nService payment unsuccessful";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("cash"))
                        {
                            admin.userPaymentTransactionsName.add("Orange Mobile Service (Cash on delivery)");
                            admin.userPaymentTransactionsCost.add(ServCost);
                            return "The payment is done successfully by cash on delivery\nThanks for using Orange Mobile Service ";
                        }
            }
        }
        return "You have entered a wrong Service Name please try again ... ";
    }
    public String internetServiceChoice(String Email ,String password,String serviceProvider,double amount,String paymentForm, Functions function, Admin admin)
    {
        Customer customerObj=getCustomer(Email,password);

        if(serviceProvider.toLowerCase().equals("vodafone"))
        {
            InternetService internetV = new VodafoneInternetService();
            customerObj.Service = new VodafoneService();
            internetV = customerObj.Service.createInternetPaymentService();
            ServCost = internetV.ServiceCost(amount);
            if(function.isAviOffer())
            {
                AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                    {
                        customerObj.creditCard.pay(AftDis);

                        customerObj.serviceNameArray.add("Vodafone Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("Vodafone Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using Vodafone Internet Service \n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else{
                        return "There's no enough money in Wallet \n Service payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(AftDis))
                    {
                        customerObj.wallet.pay(AftDis);
                        customerObj.serviceNameArray.add("Vodafone Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("Vodafone Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using Vodafone Internet Service\n "+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet \n Service payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("Vodafone Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(AftDis);
                    function.setAviOffer(false);
                    return "The payment is done successfully by cash on delivery\nThanks for using Vodafone Internet Service ";
                }
            }
            else if(function.isAviSIOffer())
            {
                if(function.isSpecInt())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getInternetOffers(function)/100));
                    if(paymentForm.toLowerCase().equals("creditcard"))
                    {
                        if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                        {
                            customerObj.creditCard.pay(AftDis);
                            customerObj.serviceNameArray.add("Vodafone Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("Vodafone Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using Vodafone Internet Service \n"+customerObj.creditCard.DisplayCreditBalance();

                        }
                        else{

                            return "There's no enough money in CreditCard\nService payment unsuccessful ";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("wallet"))
                    {
                        if(customerObj.wallet.checkWalletBalance(AftDis))
                        {
                            customerObj.wallet.pay(AftDis);

                            customerObj.serviceNameArray.add("Vodafone Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("Vodafone Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using Vodafone Internet Service\n"+customerObj.wallet.DisplayWalletBalance();
                        }
                        else
                        {
                            return  "There's no enough money in Wallet\n Service payment unsuccessful";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("cash"))
                    {
                        admin.userPaymentTransactionsName.add("Vodafone Internet Service (Cash on delivery)");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        return "The payment is done successfully by cash on delivery\nThanks for using Vodafone Internet Service";
                    }
                }
            }
            else
            {
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                    {
                        customerObj.creditCard.pay(ServCost);

                        customerObj.serviceNameArray.add("Vodafone Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Vodafone Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Vodafone Internet Service\n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else{
                        return "There's no enough money in CreditCard\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(ServCost))
                    {
                        customerObj.wallet.pay(ServCost);
                        customerObj.serviceNameArray.add("Vodafone Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Vodafone Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Vodafone Internet Service \n"+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("Vodafone Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(ServCost);
                    return "The payment is done successfully by cash on delivery\nThanks for using Vodafone Internet Service ";
                }
            }
        }
        else if(serviceProvider.toLowerCase().equals("etisalat"))
        {
            InternetService internetE = new EtisalatInternetService();
            customerObj.Service = new EtisalatService();
            internetE = customerObj.Service.createInternetPaymentService();
            ServCost = internetE.ServiceCost(amount);
            if(function.isAviOffer())
            {
                AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                    {
                        customerObj.creditCard.pay(AftDis);

                        customerObj.serviceNameArray.add("Etisalat Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("Etisalat Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using Etisalat Internet Service \n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else
                    {
                        return "There's no enough money in CreditCard\nService payment unsuccessful";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(AftDis))
                    {
                        customerObj.wallet.pay(AftDis);

                        customerObj.serviceNameArray.add("Etisalat Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("Etisalat Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using Etisalat Internet Service\n"+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("Etisalat Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(AftDis);
                    function.setAviOffer(false);
                    return "The payment is done successfully by cash on delivery\nThanks for using Etisalat Internet Service";
                }

            }
            else if(function.isAviSIOffer())
            {
                if(function.isSpecInt())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getMobileOffers(function)/100));
                    if(paymentForm.toLowerCase().equals("creditcard"))
                    {
                        if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                        {
                            customerObj.creditCard.pay(AftDis);

                            customerObj.serviceNameArray.add("Etisalat Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("Etisalat Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using Etisalat Internet Service\n"+customerObj.creditCard.DisplayCreditBalance();

                        }
                        else
                        {
                            return "There's no enough money in CreditCard\nService payment unsuccessful";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("wallet"))
                    {
                        if(customerObj.wallet.checkWalletBalance(AftDis))
                        {
                            customerObj.wallet.pay(AftDis);

                            customerObj.serviceNameArray.add("Etisalat Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("Etisalat Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using Etisalat Internet Service\n"+customerObj.wallet.DisplayWalletBalance();
                        }
                        else
                        {
                            return "There's no enough money in Wallet\nService payment unsuccessful";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("cash"))
                    {
                        admin.userPaymentTransactionsName.add("Etisalat Internet Service (Cash on delivery)");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        return "The payment is done successfully by cash on delivery\nThanks for using Etisalat Internet Service";
                    }
                }
            }
            else
            {
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                    {
                        customerObj.creditCard.pay(ServCost);

                        customerObj.serviceNameArray.add("Etisalat Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Etisalat Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Etisalat Internet Service\n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else
                    {
                        return "There's no enough money in CreditCard\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(ServCost))
                    {
                        customerObj.wallet.pay(ServCost);

                        customerObj.serviceNameArray.add("Etisalat Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Etisalat Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Etisalat Internet Service\n"+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("Etisalat Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(ServCost);
                    return "The payment is done successfully by cash on delivery\nThanks for using Etisalat Internet Service ";

                }
            }
        }
        else if(serviceProvider.toLowerCase().equals("we"))
        {
            InternetService internetW = new WeInternetService();
            customerObj.Service = new WeService();
            internetW = customerObj.Service.createInternetPaymentService();
            ServCost = internetW.ServiceCost(amount);
            if(function.isAviOffer())
            {
                AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                    {
                        customerObj.creditCard.pay(AftDis);

                        customerObj.serviceNameArray.add("We Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("We Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using We Internet Service \n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else
                    {
                        return "There's no enough money in CreditCard\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(AftDis))
                    {
                        customerObj.wallet.pay(AftDis);

                        customerObj.serviceNameArray.add("We Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("We Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using We Internet Service\n "+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("We Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(AftDis);
                    function.setAviOffer(false);
                    return "The payment is done successfully by cash on delivery\nThanks for using We Internet Service ";
                }
            }
            else if(function.isAviSIOffer())
            {
                if(function.isSpecInt())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getMobileOffers(function)/100));
                    if(paymentForm.toLowerCase().equals("creditcard"))
                    {
                        if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                        {
                            customerObj.creditCard.pay(AftDis);

                            customerObj.serviceNameArray.add("We Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("We Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using We Internet Service\n"+customerObj.creditCard.DisplayCreditBalance();

                        }
                        else
                        {
                            return "There's no enough money in CreditCard\nService payment unsuccessful";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("wallet"))
                    {
                        if(customerObj.wallet.checkWalletBalance(AftDis))
                        {
                            customerObj.wallet.pay(AftDis);

                            customerObj.serviceNameArray.add("We Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("We Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using We Internet Service\n"+customerObj.wallet.DisplayWalletBalance();

                        }
                        else
                        {
                            return "There's no enough money in Wallet\nService payment unsuccessful";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("cash"))
                    {
                        admin.userPaymentTransactionsName.add("We Internet Service (Cash on delivery)");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        return "The payment is done successfully by cash on delivery\nThanks for using We Internet Service ";
                    }
                }
            }
            else
            {
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                    {
                        customerObj.creditCard.pay(ServCost);

                        customerObj.serviceNameArray.add("We Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("We Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using We Internet Service\n "+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else
                    {
                        return "There's no enough money in CreditCard\nService payment unsuccessful";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(ServCost))
                    {
                        customerObj.wallet.pay(ServCost);

                        customerObj.serviceNameArray.add("We Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("We Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using We Internet Service\n"+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("We Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(ServCost);
                    return "The payment is done successfully by cash on delivery\nThanks for using We Internet Service";
                }
            }
        }
        else if(serviceProvider.toLowerCase().equals("orange"))
        {
            InternetService internetO;
            customerObj.Service = new OrangeService();
            internetO = customerObj.Service.createInternetPaymentService();
            ServCost = internetO.ServiceCost(amount);
            if(function.isAviOffer())
            {

                AftDis=ServCost-(ServCost*(customerObj.getOverallOffer(function)/100));
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                    {
                        customerObj.creditCard.pay(AftDis);

                        customerObj.serviceNameArray.add("Orange Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("Orange Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using Orange Internet Service\n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else{
                        return "There's no enough money in CreditCard\nService payment unsuccessful";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(AftDis))
                    {
                        customerObj.wallet.pay(AftDis);

                        customerObj.serviceNameArray.add("Orange Internet Service");
                        customerObj.serviceCostArray.add(AftDis);
                        admin.userPaymentTransactionsName.add("Orange Internet Service");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        function.setAviOffer(false);
                        return "Thanks for using Orange Internet Service\n"+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("Orange Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(AftDis);
                    function.setAviOffer(false);
                    return "The payment is done successfully by cash on delivery\nThanks for using Orange Internet Service ";
                }



            }
            else if(function.isAviSIOffer())
            {
                if(function.isSpecInt())
                {
                    AftDis=ServCost-(ServCost*(customerObj.getMobileOffers(function)/100));
                    if(paymentForm.toLowerCase().equals("creditcard"))
                    {
                        if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                        {
                            customerObj.creditCard.pay(AftDis);

                            customerObj.serviceNameArray.add("Orange Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("Orange Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using Orange Internet Service \n"+customerObj.creditCard.DisplayCreditBalance();

                        }
                        else{
                            return "There's no enough money in CreditCard\nService payment unsuccessful ";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("wallet"))
                    {
                        if(customerObj.wallet.checkWalletBalance(AftDis))
                        {
                            customerObj.wallet.pay(AftDis);

                            customerObj.serviceNameArray.add("Orange Internet Service");
                            customerObj.serviceCostArray.add(AftDis);
                            admin.userPaymentTransactionsName.add("Orange Internet Service");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "Thanks for using Orange Internet Service\n"+customerObj.wallet.DisplayWalletBalance();

                        }
                        else
                        {
                            return "There's no enough money in Wallet\nService payment unsuccessful";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("cash"))
                    {
                        admin.userPaymentTransactionsName.add("Orange Interner Service (Cash on delivery)");
                        admin.userPaymentTransactionsCost.add(AftDis);
                        return "The payment is done successfully by cash on delivery\nThanks for using Orange Internet Service ";
                    }
                }
            }
            else
            {
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(ServCost))
                    {
                        customerObj.creditCard.pay(ServCost);

                        customerObj.serviceNameArray.add("Orange Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Orange Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Orange Internet Service \n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else{
                        return "There's no enough money in CreditCard\nService payment unsuccessful ";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(ServCost))
                    {
                        customerObj.wallet.pay(ServCost);

                        customerObj.serviceNameArray.add("Orange Internet Service");
                        customerObj.serviceCostArray.add(ServCost);
                        admin.userPaymentTransactionsName.add("Orange Internet Service");
                        admin.userPaymentTransactionsCost.add(ServCost);
                        return "Thanks for using Orange Internet Service \n"+customerObj.wallet.DisplayWalletBalance();
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nService payment unsuccessful";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("Orange Internet Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(ServCost);
                    return "The payment is done successfully by cash on delivery\nThanks for using Orange Internet Service ";
                }
            }
        }
        return "Service payment unsuccessful";
    }
    public String landlineServiceChoice(String kindOfReceipt,String email,String password,String paymentForm,Functions function ,Admin admin)
    {
        Customer customerObj = getCustomer(email,password);
        if(kindOfReceipt.toLowerCase().equals("monthly"))
        {
        LandlineService monthly = new MonthlyReceipt();
        customerObj.Landline = new MonthlyService();
        monthly = customerObj.Landline.createReceipt();
        Landlinecost = monthly.serviceCost();
        if(function.isAviOffer())
        {
                AftDis=Landlinecost-(Landlinecost*(customerObj.getOverallOffer(function)/100));
                        if(paymentForm.toLowerCase().equals("creditcard"))
                        {
                            if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                            {
                                customerObj.creditCard.pay(AftDis);
                                customerObj.serviceNameArray.add("Landline Monthly Service");
                                customerObj.serviceCostArray.add(AftDis);
                                admin.userPaymentTransactionsName.add("Landline Monthly Service");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return customerObj.creditCard.DisplayCreditBalance()+"\nLandline Monthly Service payment done successfully";
                            }
                            else{
                                return "There's no enough money in CreditCard\nThe Landline Monthly Service payment unsuccessful" ;
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("wallet"))
                        {
                            if(customerObj.wallet.checkWalletBalance(AftDis))
                            {
                                customerObj.wallet.pay(AftDis);
                                customerObj.serviceNameArray.add("Landline Monthly Service");
                                customerObj.serviceCostArray.add(AftDis);
                                admin.userPaymentTransactionsName.add("Landline Monthly Service");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return  customerObj.wallet.DisplayWalletBalance()+"\nThe Landline Monthly Service payment done successfully ";
                            }
                            else
                            {
                                return "There's no enough money in Wallet\nThe Landline Monthly Service payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("cash"))
                        {
                            admin.userPaymentTransactionsName.add("Landline Monthly Service (Cash on delivery)");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            function.setAviOffer(false);
                            return "The Landline Monthly payment is done successfully by cash on delivery";
                        }
        }
        else if(function.isAviSLOffer())
        {
            if(function.isSpecLandl())
            {
                AftDis=Landlinecost-(Landlinecost*(customerObj.getLandlineOffers(function)/100));
                        if(paymentForm.toLowerCase().equals("creditcard"))
                        {
                            if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                            {
                                customerObj.creditCard.pay(AftDis);
                                customerObj.serviceNameArray.add("Landline Monthly Service");
                                customerObj.serviceCostArray.add(AftDis);
                                admin.userPaymentTransactionsName.add("Landline Monthly Service");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                return customerObj.creditCard.DisplayCreditBalance()+"\nThe Landline Monthly Service payment done successfully";

                            }
                            else{
                                return "There's no enough money in CreditCard\nThe Landline Service payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("wallet"))
                        {
                            if(customerObj.wallet.checkWalletBalance(AftDis))
                            {
                                customerObj.wallet.pay(AftDis);
                                customerObj.serviceNameArray.add("Landline Monthly Service");
                                customerObj.serviceCostArray.add(AftDis);
                                admin.userPaymentTransactionsName.add("Landline Monthly Service");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                return customerObj.wallet.DisplayWalletBalance()+"\nLandline Monthly Service payment done successfully ";
                            }
                            else
                            {
                                return "There's no enough money in Wallet\nThe Landline Monthly Service payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("cash"))
                        {
                            admin.userPaymentTransactionsName.add("Landline Monthly Service (Cash on delivery)");
                            admin.userPaymentTransactionsCost.add(AftDis);
                            return "The Landline Monthly payment is done successfully by cash on delivery";
                        }
            }
        }
        else
        {
                    if(paymentForm.toLowerCase().equals("creditcard"))
                    {
                        if(customerObj.creditCard.checkCreditCardBalance(Landlinecost))
                        {
                            customerObj.creditCard.pay(Landlinecost);
                            customerObj.serviceNameArray.add("Landline Monthly Service");
                            customerObj.serviceCostArray.add(Landlinecost);
                            admin.userPaymentTransactionsName.add("Landline Monthly Service");
                            admin.userPaymentTransactionsCost.add(Landlinecost);
                            return customerObj.creditCard.DisplayCreditBalance()+"\nThe Landline Monthly Service payment done successfully ";
                        }
                        else{
                            return "There's no enough money in CreditCard\nThe Landline Monthly Service payment unsuccessful";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("wallet"))
                    {
                        if(customerObj.wallet.checkWalletBalance(Landlinecost))
                        {
                            customerObj.wallet.pay(Landlinecost);
                            customerObj.serviceNameArray.add("Landline Monthly Service");
                            customerObj.serviceCostArray.add(Landlinecost);
                            admin.userPaymentTransactionsName.add("Landline Monthly Service");
                            admin.userPaymentTransactionsCost.add(Landlinecost);
                            return "The Landline Monthly Service payment done successfully \n"+customerObj.wallet.DisplayWalletBalance();
                        }
                        else
                        {

                            return "There's no enough money in Wallet\nThe Landline Monthly Service payment unsuccessful ";
                        }
                    }
                    else if(paymentForm.toLowerCase().equals("cash"))
                    {
                        admin.userPaymentTransactionsName.add("Landline Monthly Service (Cash on delivery)");
                        admin.userPaymentTransactionsCost.add(Landlinecost);
                        return "The Landline Monthly payment is done successfully by cash on delivery";
                    }
        }
        }
        else if(kindOfReceipt.toLowerCase().equals("quarter"))
        {
            LandlineService quarter= new QuarterReceipt();
            customerObj.Landline = new QuarterService();
            quarter = customerObj.Landline.createReceipt();
            Landlinecost = quarter.serviceCost();
            if(function.isAviOffer())
            {
                    AftDis=Landlinecost-(Landlinecost*(customerObj.getOverallOffer(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);
                                    customerObj.serviceNameArray.add("Landline Quarter Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Landline Quarter Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "Landline Quarter Service payment done successfully \n"+customerObj.creditCard.DisplayCreditBalance();
                                }
                                else{
                                    return "There's no enough money in CreditCard\nThe Landline Quarter Service payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);
                                    customerObj.serviceNameArray.add("Landline Quarter Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Landline Quarter Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    function.setAviOffer(false);
                                    return "The Landline Quarter Service payment done successfully\n "+customerObj.wallet.DisplayWalletBalance();
                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nThe Landline Quarter Service payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("Landline Quarter Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                function.setAviOffer(false);
                                return "The Landline Quarter Service payment is done successfully by cash on delivery";
                            }
            }
            else if(function.isAviSLOffer())
            {
                if(function.isSpecLandl())
                {
                    AftDis=Landlinecost-(Landlinecost*(customerObj.getLandlineOffers(function)/100));
                            if(paymentForm.toLowerCase().equals("creditcard"))
                            {
                                if(customerObj.creditCard.checkCreditCardBalance(AftDis))
                                {
                                    customerObj.creditCard.pay(AftDis);
                                    customerObj.serviceNameArray.add("Landline Quarter Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Landline Quarter Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return "The Landline Quarter Service payment done successfully \n"+ customerObj.creditCard.DisplayCreditBalance();

                                }
                                else{
                                    return "There's no enough money in CreditCard\nThe Landline Quarter Service payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("wallet"))
                            {
                                if(customerObj.wallet.checkWalletBalance(AftDis))
                                {
                                    customerObj.wallet.pay(AftDis);
                                    customerObj.serviceNameArray.add("Landline Quarter Service");
                                    customerObj.serviceCostArray.add(AftDis);
                                    admin.userPaymentTransactionsName.add("Landline Quarter Service");
                                    admin.userPaymentTransactionsCost.add(AftDis);
                                    return "Landline Quarter Service payment done successfully \n"+ customerObj.wallet.DisplayWalletBalance();

                                }
                                else
                                {
                                    return "There's no enough money in Wallet\nThe Landline Quarter Service payment unsuccessful ";
                                }
                            }
                            else if(paymentForm.toLowerCase().equals("cash"))
                            {
                                admin.userPaymentTransactionsName.add("Landline Quarter Service (Cash on delivery)");
                                admin.userPaymentTransactionsCost.add(AftDis);
                                return "The Landline Quarter payment is done successfully by cash on delivery";
                            }
                }
            }
            else
            {

                        if(paymentForm.toLowerCase().equals("creditcard"))
                        {
                            if(customerObj.creditCard.checkCreditCardBalance(Landlinecost))
                            {
                                customerObj.creditCard.pay(Landlinecost);
                                customerObj.serviceNameArray.add("Landline Quarter Service");
                                customerObj.serviceCostArray.add(Landlinecost);
                                admin.userPaymentTransactionsName.add("Landline Quarter Service");
                                admin.userPaymentTransactionsCost.add(Landlinecost);
                                return "The Landline Quarter Service payment done successfully\n "+customerObj.creditCard.DisplayCreditBalance();
                            }
                            else{
                                return "There's no enough money in CreditCard \nThe Landline Quarter Service payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("wallet"))
                        {
                            if(customerObj.wallet.checkWalletBalance(Landlinecost))
                            {
                                customerObj.wallet.pay(Landlinecost);
                                customerObj.serviceNameArray.add("Landline Quarter Service");
                                customerObj.serviceCostArray.add(Landlinecost);
                                admin.userPaymentTransactionsName.add("Landline Quarter Service");
                                admin.userPaymentTransactionsCost.add(Landlinecost);
                                return "The Landline Quarter Service payment done successfully\n "+customerObj.wallet.DisplayWalletBalance();
                            }
                            else
                            {
                                System.out.println();
                                System.out.println("");
                                System.out.println();
                                return "There's no enough money in Wallet\nThe Landline Quarter Service payment unsuccessful ";
                            }
                        }
                        else if(paymentForm.toLowerCase().equals("cash"))
                        {
                            admin.userPaymentTransactionsName.add("Landline Quarter Service (Cash on delivery)");
                            admin.userPaymentTransactionsCost.add(Landlinecost);
                            return "The Landline Quarter payment is done successfully by cash on delivery";
                        }


            }
        }
        return "You have entered a wrong kind of Receipt";
    }

    public String donationServiceChoice(String serviceName,String email,String password,double amount,String paymentForm,Admin admin)
    {
        Customer customerObj=getCustomer(email,password);
    if(serviceName.toLowerCase().equals("cancer hospital donation"))
    {
        customerObj.Donation = new CancerHospitalService();
        DonationService Donation = customerObj.Donation.createDonationAmount();
        DonationCost=Donation.donationCost(amount);
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(DonationCost))
                    {
                        customerObj.creditCard.pay(DonationCost);
                        admin.userPaymentTransactionsName.add("The Cancer Hospital Donation Service");
                        admin.userPaymentTransactionsCost.add(DonationCost);
                        return  "Thanks for your donation to The Cancer Hospital\n"+customerObj.creditCard.DisplayCreditBalance();
                    }
                    else{

                        return "There's no enough money in CreditCard\nYour donation to Cancer Hospital has been canceled";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(DonationCost))
                    {
                        customerObj.wallet.pay(DonationCost);
                        admin.userPaymentTransactionsName.add("The Cancer Hospital Donation Service");
                        admin.userPaymentTransactionsCost.add(DonationCost);
                        return  customerObj.wallet.DisplayWalletBalance()+"\nThanks for your donation to The Cancer Hospital";
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nYour donation to Cancer Hospital has been canceled";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("The Cancer Hospital Donation Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(DonationCost);
                    return "The Donation is done successfully by cash on delivery\nThanks for your Donation";

                }
    }
    else if(serviceName.toLowerCase().equals("NGOs donation"))
    {
        customerObj.Donation=new NGOsService();
        DonationService Donation= customerObj.Donation.createDonationAmount();
        DonationCost=Donation.donationCost(amount);

                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(DonationCost))
                    {
                        customerObj.creditCard.pay(DonationCost);
                        admin.userPaymentTransactionsName.add("The NGOsDonation Donation Service");
                        admin.userPaymentTransactionsCost.add(DonationCost);
                        return customerObj.creditCard.DisplayCreditBalance()+"\nThanks for your Donation";
                    }
                    else
                    {
                        return "There's no enough money in CreditCard\nYour donation to NGOs has been canceled";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(DonationCost))
                    {
                        customerObj.wallet.pay(DonationCost);
                        admin.userPaymentTransactionsName.add("The NGOsDonation Donation Service");
                        admin.userPaymentTransactionsCost.add(DonationCost);
                        return customerObj.wallet.DisplayWalletBalance()+"\nThanks for your Donation";
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nYour donation to NGOs has been canceled";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("The NGOsDonation Donation Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(DonationCost);
                    return "The Donation is done successfully by cash on delivery\nThanks for your Donation";
                }
    }
    else if(serviceName.toLowerCase().equals("school donation"))
    {
        customerObj.Donation=new SchoolsService();
        DonationService Donation= customerObj.Donation.createDonationAmount();
        DonationCost=Donation.donationCost(amount);
                if(paymentForm.toLowerCase().equals("creditcard"))
                {
                    if(customerObj.creditCard.checkCreditCardBalance(DonationCost))
                    {
                        customerObj.creditCard.pay(DonationCost);
                        admin.userPaymentTransactionsName.add("The School Donation Service");
                        admin.userPaymentTransactionsCost.add(DonationCost);
                        return customerObj.creditCard.DisplayCreditBalance()+"\nThanks for your Donation";
                    }
                    else
                    {
                        return "There's no enough money in CreditCard\nYour donation to Schools has been canceled";
                    }
                }
                else if(paymentForm.toLowerCase().equals("wallet"))
                {
                    if(customerObj.wallet.checkWalletBalance(DonationCost))
                    {
                        customerObj.wallet.pay(DonationCost);
                        admin.userPaymentTransactionsName.add("The School Donation Service");
                        admin.userPaymentTransactionsCost.add(DonationCost);
                        return customerObj.wallet.DisplayWalletBalance()+"\nThanks for your Donation";
                    }
                    else
                    {
                        return "There's no enough money in Wallet\nYour donation to Schools has been canceled";
                    }
                }
                else if(paymentForm.toLowerCase().equals("cash"))
                {
                    admin.userPaymentTransactionsName.add("The School Donation Service (Cash on delivery)");
                    admin.userPaymentTransactionsCost.add(DonationCost);
                    return "The Donation is done successfully by cash on delivery\nThanks for your Donation";

                }
    }
    return "You have entered a wrong Service Name please try again ... ";
}
public String addToWallet(String email,String password ,double amount, Admin admin)
{
    Customer customerObj=getCustomer(email,password);
    if(customerObj.wallet.checkCreditCardBalance(customerObj.creditCard, amount))
    {
        customerObj.wallet.addingToWallet(customerObj.creditCard, amount);
        admin.userAddToWalletTransactionsAmount.add(amount);
        return customerObj.creditCard.DisplayCreditBalance()+"\n"+customerObj.wallet.DisplayWalletBalance();
    }
    else
    {
        return "There's no enough money in CreditCard";
    }
}

public String serviceRefund(String email,String password, String serviceToRefund,String adminAceptance,Admin adminObj) //serviceToRefund EX: internet orange service
{

    Customer customerObj=getCustomer(email,password);
    for(int i=0;i<customerObj.serviceNameArray.size();i++)
    {
        if(serviceToRefund.toLowerCase().equals(customerObj.serviceNameArray.get(i).toLowerCase()))
        {
            numOfService=i;
            break;
        }
    }
    if(adminObj.acceptRefund(adminAceptance))
    {
        refundedAmount=customerObj.serviceCostArray.get((numOfService));
        adminObj.userRefundTransactionsName.add(customerObj.serviceNameArray.get(numOfService));
        adminObj.userRefundTransactionsAmount.add(customerObj.serviceCostArray.get(numOfService));
        customerObj.wallet.refund(refundedAmount);
        customerObj.serviceNameArray.remove((numOfService));
        customerObj.serviceCostArray.remove((numOfService));
        return "The admin accept to return the refund\n"+"The Refunded Amount = "+refundedAmount + " will be added again to your wallet"+"\n"+customerObj.wallet.DisplayWalletBalance();
    }
    else {
        return  "The admin refuse to return the refund ";
    }
}

public String checkOffers(Functions function)
{
    if(function.isAviOffer())
    {
        return "There's an available overall offer on the first transaction ";
    }
    if(function.isAviSIOffer()||function.isAviSMOffer() ||function.isAviSLOffer())
    {
        if(function.isSpecMob()&&function.isSpecInt()&&function.isSpecLandl())
        {
            return "There's an available Special offer on: \nMobile Service\nInternet Service\nLandline Service";
        }
        else if(function.isSpecMob()&&function.isSpecInt())
        {
            return "There's an available Special offer on: \nMobile Service\nInternet Service";
        }
        else if(function.isSpecMob()&&function.isSpecLandl())
        {
            return "There's an available Special offer on: \nMobile Service\nLandline Service";
        }

        else if(function.isSpecInt()&&function.isSpecLandl())
        {
            return "There's an available Special offer on: \nInternet Service\nLandline Service";
        }

        else if(function.isSpecMob())
        {
            return "There's an available Special offer on: \nMobile Service";
        }
        else if(function.isSpecInt())
        {
            return "There's an available Special offer on: \nInternet Service";
        }
        else if(function.isSpecLandl())
        {
            return "There's an available Special offer on: \nLandline Service";
        }
    }
    if(!function.isAviOffer() && !function.isAviSIOffer() && !function.isAviSMOffer() && !function.isAviSLOffer())
    {
        return "There are no available offers right now";
    }

        return "There are no available offers right now";
}
public String searchForAnyService(String searchFor, String provider,String receipt,Search search )
{
    if(searchFor.toLowerCase().equals("mobile") || searchFor.toLowerCase().equals("mobile service") )
    {
        return search.searchMobileService(provider);
    }
    else if(searchFor.toLowerCase().equals("internet")||searchFor.toLowerCase().equals("internet service"))
    {
        return search.searchInternetService(provider);
    }
    else if(searchFor.toLowerCase().equals("landline")||searchFor.toLowerCase().equals("landline service"))
    {
        return search.searchLandlineService(receipt);
    }
    else if(searchFor.toLowerCase().equals("donation")||searchFor.toLowerCase().equals("donation service"))
    {
        return search.searchDonationService();
    }
    return "You are searching for a not available something";
}
}
