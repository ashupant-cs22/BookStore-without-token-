package com.example.bookstoreapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    public String bookName;
    public String authorName;
    public String bookDescription;
    public String bookImage;
    public Integer price;
    public Integer quantity;

}
