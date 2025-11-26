package org.example.buoi1_server.Buoi1_CRUD_1Bang.Model.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CaSiResponse {

    private Integer id;

    private String tenCaSi;

    private String que;

    private Integer tuoi;

    private String soDienThoai;

}
