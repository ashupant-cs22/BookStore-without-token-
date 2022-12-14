package com.example.bookstoreapp.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Table(name = "order_details")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @OneToMany(fetch = FetchType.LAZY,orphanRemoval=true)
    @org.hibernate.annotations.ForeignKey(name = "none")
    private List<Cart> cart;
    @ManyToMany
    private List<BookStore> book;
    private int userId;
    private int orderQty;
    private String address;
    private int orderPrice;
    private LocalDate orderedAt;
    private boolean cancel;

    public Order(int userId, String address, List<Cart> cart, List<BookStore> book, LocalDate orderedAt, int totalOrderQty, int totalPrice, boolean cancel) {
        this.userId=userId;
        this.cart=cart;
        this.orderedAt=orderedAt;
        this.orderQty=totalOrderQty;
        this.orderPrice=totalPrice;
        this.cancel=cancel;
        this.address=address;
        this.book=book;
    }
}