package com.I2I.I2IBaceknd.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.I2I.I2IBaceknd.Repository.MasterCustomerRepo;

@Service
public class MasterCustomerService {

    @Autowired
    private MasterCustomerRepo customerrepo; 
    


      public Map<String, Object> saveCustomer(JSONObject obj) {
        Map<String, Object> param = new HashMap<>();
    
        param.put("customer_id", obj.get("customer_id")); // for update, can be null for insert
        param.put("uuid", genrate_uuid( obj.get("first_name"), obj.get("date_of_birth"))); // assume frontend/client generates UUID string for tracking
        param.put("first_name", obj.get("first_name"));
        param.put("middle_name", obj.get("middle_name"));
        param.put("last_name", obj.get("last_name"));
        param.put("phone_no", obj.get("phone_no"));
        String dobString = obj.get("date_of_birth").toString();
        Date dob = Date.valueOf(dobString); // Converts to java.sql.Date
        param.put("date_of_birth",dob); // must be formatted as yyyy-MM-dd
        param.put("email", obj.get("email"));
        param.put("nationality", obj.get("nationality"));
         System.out.println("param"+param);
    
        return customerrepo.saveCustomer(param); // similar to save_inqury call
    }


    private String genrate_uuid(Object firstNameObj, Object dobObj) {
        if (firstNameObj == null || dobObj == null) {
            throw new IllegalArgumentException("First name and date of birth must not be null");
        }
    
        String firstName = firstNameObj.toString().trim();
        String dob = dobObj.toString().trim(); // Expected format: "yyyy-MM-dd" or "dd/MM/yyyy"
    
        // Extract first 2 characters of first name (capitalize first)
        String firstTwo = firstName.length() >= 2 ? firstName.substring(0, 2) : firstName;
        firstTwo = firstTwo.substring(0, 1).toUpperCase() + firstTwo.substring(1).toLowerCase();
    
        // Extract last two digits of year from dob
        String year = "";
        if (dob.contains("-")) { // Format: yyyy-MM-dd
            year = dob.split("-")[0];
        } else if (dob.contains("/")) { // Format: dd/MM/yyyy
            year = dob.split("/")[2];
        }
    
        String lastTwoOfYear = year.length() >= 2 ? year.substring(year.length() - 2) : "00";
    
        return firstTwo + lastTwoOfYear;
    }


    public List<Map<String, Object>> get_customer_list() {
        return customerrepo.get_customer_list();
    }
}
