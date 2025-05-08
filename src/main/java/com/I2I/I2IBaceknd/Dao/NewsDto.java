package com.I2I.I2IBaceknd.Dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
	private Long id;
    private String header;
    private String description;
    private String image; // Base64 encoded string

}
