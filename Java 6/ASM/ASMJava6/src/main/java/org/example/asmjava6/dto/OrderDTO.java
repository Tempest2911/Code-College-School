package org.example.asmjava6.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDTO {
    private String username;
    private String address;
    private List<OrderDetailDTO> cart;

    @Data
    public static class OrderDetailDTO {
        private Integer id;
        private Double price;
        private Integer qty;
    }
}