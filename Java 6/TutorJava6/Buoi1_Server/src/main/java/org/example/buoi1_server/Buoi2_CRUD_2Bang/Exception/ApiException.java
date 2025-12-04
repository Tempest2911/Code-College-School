package org.example.buoi1_server.Buoi2_CRUD_2Bang.Exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException{
    private String code;

    public ApiException(String code, String message){
        super(message);
        this.code = code;
    }


}
