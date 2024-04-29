package com.fci.advanced.Fawry.Controllers;

import com.fci.advanced.Fawry.BusinessLogic.AdminBsl;
import com.fci.advanced.Fawry.Functions.Functions;
import com.fci.advanced.Fawry.Patterns.Admin_CustomerObserverPattern.Admin;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
public class AdminCon {
    public AdminBsl adminBsl = new AdminBsl();
  public Functions function = new Functions();
  public  Admin admin = new Admin();
    public AdminCon(){}
    @PostMapping(value = "setOffer")
    public String SetOffer(@RequestParam("O_S") int O_S, @RequestParam(value = "SM_SI_SL" ,required = false) int SM_SI_SL, @RequestParam("DiscAmo") double DiscAmo)
    {
        return  adminBsl.addOffer(O_S,SM_SI_SL, DiscAmo,this.function);
    }
    @PostMapping(value = "addNewService")
    public String addNewService(@RequestParam("serviceName") String serviceName)
    {
        return  adminBsl.addNewService(this.admin,serviceName);
    }
    @GetMapping(value = "getaUserTransactionsName")
    public ArrayList<String> getaUserTransactionsName(@RequestParam("transactionName") String transactionName)
    {
        return adminBsl.listOfAllUserTransactionsName(this.admin ,transactionName);
    }
    @GetMapping(value = "getaUserTransactionsCost")
    public ArrayList<Double> getaUserTransactionsCost(@RequestParam("transactionName") String transactionName)
    {
        return adminBsl.listOfAllUserTransactionsCost(transactionName,this.admin);
    }

}
