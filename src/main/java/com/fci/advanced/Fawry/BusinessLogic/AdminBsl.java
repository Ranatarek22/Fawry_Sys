package com.fci.advanced.Fawry.BusinessLogic;

import com.fci.advanced.Fawry.Functions.Functions;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.Admin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminBsl {
    public AdminBsl(){}
    String addSer;
    public String addNewService(Admin admin , String serviceName)
    {
        admin.addService(addSer);
        return "You have added new Service: " + serviceName ;
    }
    public String addOffer(int O_S,int SM_SI_SL,double DiscAmo,Functions function)
    {
        if(O_S==1)
        {
            function.overallDiscount(DiscAmo);
            function.setAviOffer(true);
            return "An Overall Discount has been added with amount = "+DiscAmo +"% ";
        }
        if(O_S==2)
        {
            if(SM_SI_SL==1)
            {
                function.specificMobileDiscount(DiscAmo);
                function.setAviSMOffer(true);
                function.setSpecMob(true);
                return "A Specific Discount has been added on Mobile Services with amount = "+DiscAmo +"% ";
            }
            else if(SM_SI_SL==2)
            {
                function.specificInternetDiscount(DiscAmo);
                function.setAviSIOffer(true);
                function.setSpecInt(true);
                return "A Specific Discount has been added on Internet Services with amount = "+DiscAmo +"% ";
            }
            else if(SM_SI_SL==3)
            {
                function.specificLandlineDiscount(DiscAmo);
                function.setAviSLOffer(true);
                function.setSpecLandl(true);
                return "A Specific Discount has been added on Landline Services with amount = "+DiscAmo +"% ";
            }
        }
        return "There is no offers has been added";
    }
    public ArrayList<String> listOfAllUserTransactionsName(Admin admin, String Payment_Refund)
    {
        if(Payment_Refund.toLowerCase().equals("payment transactions"))
        {
            if(admin.userPaymentTransactionsName.size()>0)
            {
                return admin.userPaymentTransactionsName;
            }
            else
            {
                return null;
            }
        }
        else if(Payment_Refund.toLowerCase().equals("refund transactions"))
        {
            if(admin.userRefundTransactionsName.size()>0)
            {
               return admin.userRefundTransactionsName;
            }
            else
            {
                return null;
            }
        }
        return  null;
    }
        public ArrayList<Double> listOfAllUserTransactionsCost(String Payment_AddToWal_Refund,Admin admin)
    {
        if(Payment_AddToWal_Refund.toLowerCase().equals("payment transactions"))
        {
            if(admin.userPaymentTransactionsName.size()>0)
            {
                return admin.userPaymentTransactionsCost;
            }
            else
            {
                return null;
            }
        }
        else if(Payment_AddToWal_Refund.toLowerCase().equals("add to wallet transactions"))
        {
            if(admin.userAddToWalletTransactionsAmount.size()>0)
            {
                return admin.userAddToWalletTransactionsAmount;
            }
            else
            {
                return null;
            }
        }
        else if(Payment_AddToWal_Refund.toLowerCase().equals("refund transactions"))
        {
            if(admin.userRefundTransactionsName.size()>0)
            {
                return admin.userRefundTransactionsAmount;
            }
            else
            {
                return null;
            }
        }
        return  null;
    }
}
