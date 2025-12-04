package org.example.j6backendjava.enity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private String id;
    private String name;
    private boolean gender;
    private double mark;
}
