package com.I2I.I2IBaceknd.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.I2I.I2IBaceknd.Dao.dao;

@Repository
public class MasterCustomerRepo extends dao {

        public Map<String, Object> saveCustomer(Map<String, Object> param) {
            String sql = "";

            if (param.get("customer_id") == null) {
                System.out.println("Inserting new customer...");

                sql += "INSERT INTO Master_customer ( ";
                sql += "uuid, first_name, middle_name, last_name, phone_no, date_of_birth, email, nationality ";
                sql += ") VALUES ( ";
                sql += ":uuid, :first_name, :middle_name, :last_name, :phone_no, :date_of_birth, :email, :nationality ";
                sql += ");";

            } else {
                System.out.println("Updating existing customer...");
                sql += "UPDATE Master_customer SET ";
                sql += "uuid = :uuid, ";
                sql += "first_name = :first_name, ";
                sql += "middle_name = :middle_name, ";
                sql += "last_name = :last_name, ";
                sql += "phone_no = :phone_no, ";
                sql += "date_of_birth = :date_of_birth, ";
                sql += "email = :email, ";
                sql += "nationality = :nationality ";
                sql += "WHERE customer_id = :customer_id;";
            }

            Long id = this.Save(sql, param, "customer_id");

            Map<String, Object> result = new HashMap<>();
            if (param.get("customer_id") != null) {
                result.put("customer_id", id);
                result.put("message", "Customer updated successfully");

            } else {
                result.put("customer_id", id);
                result.put("message", "New Customer Registered");
                result.put("Customer Registered Id",param.get("uuid"));
            }

            return result;
        }

        public List<Map<String, Object>> get_customer_list() {
           String  sql = "Select uuid, first_name, middle_name, last_name, phone_no, date_of_birth, email, nationality from Master_customer";
           return this.executeQuery(sql);
        }
}
