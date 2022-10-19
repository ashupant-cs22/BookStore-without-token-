package com.example.bookstoreapp.Model;


import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer cartId;
    @OneToOne
    @JoinColumn(name = "user_id")
    public UserDetail userDetail;

    @ManyToOne
    @JoinColumn(name = "book_id")
    public BookStore bookStore;
    public Integer quantity;
    public Integer price;

    public Cart( UserDetail userDetail,BookStore bookStore, Integer quantity,Integer price) {

        this.userDetail = userDetail;
        this.bookStore = bookStore;
        this.quantity = quantity;
        this.price=price;

    }

    public Cart() {

    }


}
