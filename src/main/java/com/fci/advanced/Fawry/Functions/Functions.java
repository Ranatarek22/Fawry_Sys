package com.fci.advanced.Fawry.Functions;
public class Functions {
    public Functions(){}
    private static double mDiscount,iDiscount,lDiscount,ODiscount;
    private static boolean  aviOffer=false,aviSMOffer=false,aviSIOffer=false,aviSLOffer=false,specMob=false,specInt=false,specLandl=false;
    
    
    public boolean isAviOffer() {
        return aviOffer;
    }

    public void setAviOffer(boolean aviOffer) {
        this.aviOffer = aviOffer;
    }

    public boolean isAviSMOffer() {
        return aviSMOffer;
    }

    public void setAviSMOffer(boolean aviSMOffer) {
        this.aviSMOffer = aviSMOffer;
    }

    public boolean isAviSIOffer() {
        return aviSIOffer;
    }

    public void setAviSIOffer(boolean aviSIOffer) {
        this.aviSIOffer = aviSIOffer;
    }

    public boolean isAviSLOffer() {
        return aviSLOffer;
    }

    public void setAviSLOffer(boolean aviSLOffer) {
        this.aviSLOffer = aviSLOffer;
    }

    public boolean isSpecMob() {
        return specMob;
    }

    public void setSpecMob(boolean specMob) {
        this.specMob = specMob;
    }

    public boolean isSpecInt() {
        return specInt;
    }

    public void setSpecInt(boolean specInt) {
        this.specInt = specInt;
    }

    public boolean isSpecLandl() {
        return specLandl;
    }

    public void setSpecLandl(boolean specLandl) {
        this.specLandl = specLandl;
    }

    public void overallDiscount(double disc)
    {
        this.ODiscount=disc;
    }

    public void specificMobileDiscount(double disc)
    {   
        this.mDiscount=disc;
    }

    public void specificInternetDiscount(double disc)
    {

        this.iDiscount=disc;

    }

    public void specificLandlineDiscount(double disc)
    {
        this.lDiscount=disc;
    }


    public double getMobileDiscount()
    {
        return mDiscount;
        
    }

    public double getInternetDiscount()
    {
        return iDiscount;
    }


    public double getLandlineDiscount()
    {
        return lDiscount;
    }

    
    public double getOverallDiscount()
    {
        return ODiscount;
    }
}
