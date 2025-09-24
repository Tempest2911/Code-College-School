package org.example.lab3;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Staff {
    private String id;
    private String fullName;
    private String photo = "photo.jpg";
    private Boolean gender = true;
    private Date birthday = new Date();
    private double salary = 12345.68;
    private Integer level = 0;
}
