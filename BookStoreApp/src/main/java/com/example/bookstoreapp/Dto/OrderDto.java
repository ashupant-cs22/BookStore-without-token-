package com.example.bookstoreapp.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

    private Integer userId;
    private Integer cartId;
    private String address;
    private Integer price;
    private boolean cancel;
}