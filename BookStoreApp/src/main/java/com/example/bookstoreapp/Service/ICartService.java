package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.CartDto;
import com.example.bookstoreapp.Model.Cart;
import java.util.List;
import java.util.Optional;

public interface ICartService {

    Cart insertItem(CartDto cartDto);

    List<Cart> listAll();

    Optional<Cart> getCartDetailsById(Integer cartId);

    Optional<Cart> deleteCartItemById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDto cartDTO);

    Cart updateQuantity(Integer id, Integer quantity);

}
