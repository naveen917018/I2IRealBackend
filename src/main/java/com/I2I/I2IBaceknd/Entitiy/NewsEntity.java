package com.I2I.I2IBaceknd.Entitiy;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key
    private String header;
    private String description;

    @Lob
    @Column(name = "image")
    private byte[] image;  // Image stored as byte array

    private boolean isActive = true;  // Default value is true when saving
    public NewsEntity(String header, String description, byte[] image, boolean isActive) {
        this.header = header;
        this.description = description;
        this.image = image;
        this.isActive = isActive;
    }

}
