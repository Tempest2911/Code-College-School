package org.example.testresttemplate.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private String id;
    private String name;
    private double mark;
    private Boolean gender;
}
