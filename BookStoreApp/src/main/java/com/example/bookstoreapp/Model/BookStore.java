package com.example.bookstoreapp.Model;

import com.example.bookstoreapp.Dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer bookId;
    public String  bookName;
    public String  authorName;
    public String  bookDescription;
    public String  bookImage;
    public Integer price;
    public Integer quantity;



    public BookStore(BookDto bookDTO){
        this.bookName=bookDTO.getBookName();
        this.authorName=bookDTO.getAuthorName();
        this.bookImage=bookDTO.getBookImage();
        this.quantity=bookDTO.getQuantity();
        this.price=bookDTO.getPrice();
        this.bookDescription=bookDTO.getBookDescription();


    }
}


