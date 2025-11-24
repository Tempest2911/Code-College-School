package org.example.buoi1_server.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CaSiRequest {
    //Model mapper: ten trong request khac vs ten trong entity (Co the hieu duoc)
    // Tu viet ham chuyen doi (Dat ten khac cung duoc)
    // Bean Utils: ten trong entity phai trung vs ten trong request


    private Integer id;

    private String tenCaSi;

    private String queQuan;

    private Integer tuoi;

    private String congTy;
}
