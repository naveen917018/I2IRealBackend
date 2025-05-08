package com.I2I.I2IBaceknd.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.I2I.I2IBaceknd.Dao.dao;

@Repository
public class MasterRepo extends dao {

    public List<Map<String, Object>> getStatusById(Integer id) {
        String sql = "SELECT status_code, status_name, created_at, updated_at FROM status_master WHERE status_id = :id";
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        return this.executeQuery(sql, param);
    }

    public List<Map<String, Object>> getAllStatuses() {
        String sql = "SELECT status_id, status_code, status_name, created_at, updated_at FROM status_master";
        return this.executeQuery(sql, new HashMap<>());
    }



    public Map<String, Object> saveMasterStatus(Map<String, Object> param) {
        String sql = "";
    
        if (param.get("status_id") == null) {
            System.out.println("Inserting new status...");
    
            sql += "INSERT INTO status_master (";
            sql += "status_code, status_name, created_at";
            sql += ") VALUES (";
            sql += ":status_code, :status_name, now()";
            sql += ");";
    
        } else {
            System.out.println("Updating existing status...");
    
            sql += "UPDATE status_master SET ";
            sql += "status_code = :status_code, ";
            sql += "status_name = :status_name, ";
            sql += "updated_at = now() ";
            sql += "WHERE status_id = :status_id;";
        }
    
        Long id = this.Save(sql, param, "status_id");
    
        Map<String, Object> result = new HashMap<>();
        result.put("status_id", id);
        result.put("message", param.get("status_id") == null
            ? "New Status Registered"
            : "Status updated successfully");
    
        return result;
    }
    
  

    
}
