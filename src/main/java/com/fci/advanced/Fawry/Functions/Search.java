package com.fci.advanced.Fawry.Functions;
import com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern.MonthlyReceipt;
import com.fci.advanced.Fawry.Patterns.LandlineAbstractFactoryPattern.QuarterReceipt;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.EtisalatInternetService;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.EtisalatMobileService;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.OrangeInternetService;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.OrangeMobileService;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.VodafoneInternetService;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.VodafoneMobileService;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.WeInternetService;
import com.fci.advanced.Fawry.Patterns.ServiceAbstractFactoryPattern.WeMobileService;

public class Search {

    public String searchMobileService(String provider){
        if(provider.toLowerCase().equals("vodafone") ){
            VodafoneMobileService vM= new VodafoneMobileService();
            return "the cost per unit of the service is "+vM.getCostPerUnit() +" L.E";
        }else if(provider.toLowerCase().equals("we")){
            WeMobileService weM= new WeMobileService();
            return "the cost per unit of the service is "+weM.getCostPerUnit()+" L.E";
        }else if(provider.toLowerCase().equals("orange")){
            OrangeMobileService oM= new OrangeMobileService();
            return "the cost per unit of the service is "+oM.getCostPerUnit()+" L.E";
        }else if(provider.toLowerCase().equals("etisalat")){
            EtisalatMobileService eM= new EtisalatMobileService();
            return "the cost per unit of the service is "+eM.getCostPerUnit()+" L.E";
        }
        else {
            return "You have entered a wrong provider";
        }
    }

    public String searchInternetService(String provider){
        if(provider.toLowerCase().equals("vodafone")){
            VodafoneInternetService vI= new VodafoneInternetService();
            return "the cost per giga of the service is "+vI.getCostPerGiga()+" L.E";
        }else if(provider.toLowerCase().equals("we")){
            WeInternetService weI= new WeInternetService();
            return "the cost per giga of the service is "+weI.getCostPerGiga()+" L.E";
        }else if(provider.toLowerCase().equals("orange")){
            OrangeInternetService oI= new OrangeInternetService();
            return "the cost per giga of the service is "+oI.getCostPerGiga()+" L.E";
        }else if(provider.toLowerCase().equals("etisalat")){
            EtisalatInternetService eI= new EtisalatInternetService();
            return "the cost per giga of the service is "+eI.getCostPerGiga()+" L.E";
        }
        else
        {
            return "You have entered a wrong provider";
        }
    }

    public String searchLandlineService(String s){
        if(s.toLowerCase().equals("quarter")){
            QuarterReceipt q=new QuarterReceipt();
            return "The Receipt is "+q.serviceCost() +" L.E";
        }else if(s.toLowerCase().equals("monthly")){
            MonthlyReceipt m =new MonthlyReceipt();
            return "The Receipt is "+m.serviceCost()+" L.E";
        }
        else
        {
            return "You have entered a wrong input";
        }
    }
    public String searchDonationService()
    {
        return "The donation services are:\n1)Cancer Hospital Donation\n2)NGOs Donation\n3)School Donation";
    }
}
