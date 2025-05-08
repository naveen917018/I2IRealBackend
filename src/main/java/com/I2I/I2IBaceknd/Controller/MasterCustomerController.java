package com.I2I.I2IBaceknd.Controller;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.I2I.I2IBaceknd.Commonfn.commonfn;
import com.I2I.I2IBaceknd.Service.MasterCustomerService;

@RestController
public class MasterCustomerController {

      
    @Autowired
    private MasterCustomerService customerservice;
    
    
    @GetMapping(path={"api/v0/get_customers_list","api/v1/get_customer_list"})
    public List<Map<String,Object>>get_customer_list(){
        List<Map<String,Object>> customer_list= customerservice.get_customer_list();
        return customer_list;
    }


    @PostMapping(path = {"/api/v0/save_customer", "/api/v1/save_customer"})
    public Map<String, Object> saveCustomer(@RequestBody String customerDetailsJson) {
        JSONObject obj = commonfn.createJSONObject(customerDetailsJson);
        return customerservice.saveCustomer(obj);
    }
    
}
