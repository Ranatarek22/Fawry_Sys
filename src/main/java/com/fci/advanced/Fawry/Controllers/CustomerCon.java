package com.fci.advanced.Fawry.Controllers;

import com.fci.advanced.Fawry.Functions.Search;
import com.fci.advanced.Fawry.BusinessLogic.CustomerBsl;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.CustomerData.Customer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerCon {
    private CustomerBsl customerBsl;
    public CustomerCon(CustomerBsl c)
    {
        this.customerBsl=c;
    }
    private  AdminCon ad = new AdminCon();
    private Search search = new Search();
    @PostMapping(value = "signup")
    public String addCustomer(@RequestBody Customer customer)
    {
        return customerBsl.add(customer);
    }
    @GetMapping(value = "signin")
    public Customer getCustomer(@RequestParam("email") String Email , @RequestParam("password")  String password)
    {
        return  customerBsl.getCustomer(Email,password);
    }
    @PostMapping(value = "signin/makemobileservice")
    public String makeMobileService(@RequestParam("Email") String Email,@RequestParam("password") String password, @RequestParam("serviceProvider") String serviceProvider, @RequestParam("amount") double amount, @RequestParam("paymentForm") String paymentForm)
    {
        return customerBsl.mobileServiceChoice(Email , password, serviceProvider, amount, paymentForm,ad.function, ad.admin);
    }
    @PostMapping(value ="signin/makeinternetservice" )
    public String makeInternetService(@RequestParam("Email") String Email,@RequestParam("password") String password, @RequestParam("serviceProvider") String serviceProvider, @RequestParam("amount") double amount, @RequestParam("paymentForm") String paymentForm)
    {
        return customerBsl.internetServiceChoice(Email , password, serviceProvider, amount, paymentForm,ad.function, ad.admin);
    }
    @PostMapping(value = "signin/makelandlineservice")
    public String makeLandLineService(@RequestParam("kindOfReceipt") String kindOfReceipt,@RequestParam("Email") String Email,@RequestParam("password") String password,@RequestParam("paymentForm") String paymentForm)
    {
        return customerBsl.landlineServiceChoice( kindOfReceipt, Email, password, paymentForm,  ad.function ,  ad.admin);
    }
    @PostMapping(value ="signin/makedonationservice")
    public String makeDonationService(@RequestParam("serviceName") String serviceName,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("amount") double amount,@RequestParam("paymentForm") String paymentForm)
    {
        return customerBsl.donationServiceChoice(serviceName,email,password, amount, paymentForm, ad.admin);
    }
    @PutMapping(value ="signin/addtowallet")
    public String addToWallet(@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("amount") double amount)
    {
        return customerBsl.addToWallet(email,password,amount,ad.admin);
    }
    @GetMapping(value = "signin/requestrefund")
    public String requestRefund(@RequestParam("email") String email,@RequestParam("password")String password,@RequestParam("serviceToRefund") String serviceToRefund,@RequestParam("adminAceptance") String adminAceptance)
    {
        return customerBsl.serviceRefund(email,password,serviceToRefund,adminAceptance,ad.admin);
    }
    @GetMapping(value = "signin/checkoffers")
    public String checkOffers()
    {
        return customerBsl.checkOffers(ad.function);
    }
    @GetMapping(value = "signin/searchforaservice")
    public String searchForAnyService(@RequestParam("searchFor") String searchFor,@RequestParam(value = "provider",required = false) String provider ,@RequestParam(value = "receipt",required = false) String receipt )
    {
        return customerBsl.searchForAnyService(searchFor,provider,receipt,this.search);
    }


}
