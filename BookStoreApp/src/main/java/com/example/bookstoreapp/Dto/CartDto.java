package com.example.bookstoreapp.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CartDto {

    public Integer userId;

    public Integer bookId;

    public Integer quantity;

    public Integer price;

    public CartDto(Integer userId, Integer bookId, Integer quantity, Integer price) {
        this.userId = this.getUserId();
        this.bookId = this.getBookId();
        this.quantity = this.getQuantity();
        this.price = this.getPrice();
    }

}
