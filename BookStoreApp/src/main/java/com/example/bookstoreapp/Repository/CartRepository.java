package com.example.bookstoreapp.Repository;

import com.example.bookstoreapp.Dto.CartDto;
import com.example.bookstoreapp.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query(value = "update cart set quantity=12 where user_id=2",nativeQuery = true)
    Cart updateQuantity(int cartId, int quantity, CartDto cartDto);

    @Query(value = "select * FROM cart where user_id=:userId",nativeQuery = true)
    List<Cart> getByUserId(Integer userId);
}
