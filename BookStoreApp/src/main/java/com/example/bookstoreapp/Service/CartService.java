package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.CartDto;
import com.example.bookstoreapp.Exception.BookStoreExceptionMessage;
import com.example.bookstoreapp.Model.BookStore;
import com.example.bookstoreapp.Model.Cart;
import com.example.bookstoreapp.Model.UserDetail;
import com.example.bookstoreapp.Repository.BookRepository;
import com.example.bookstoreapp.Repository.CartRepository;
import com.example.bookstoreapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public Cart insertItem(CartDto cartDto) {
        Optional<BookStore> bookStore = bookRepository.findById(cartDto.getBookId());
        Optional<UserDetail> userDetail = userRepository.findById(cartDto.getUserId());
        if (bookStore.isPresent() && userDetail.isPresent()) {
            if (cartDto.getQuantity() <= bookStore.get().getQuantity()) {
                int quantity = bookStore.get().getQuantity() - cartDto.getQuantity();
                bookStore.get().setQuantity(quantity);
                bookRepository.save(bookStore.get());
                Cart cart = new Cart(userDetail.get(),bookStore.get(), cartDto.getQuantity(),bookStore.get().getPrice()* cartDto.quantity);
                cartRepository.save(cart);
                return cart;

            } else {
                throw new BookStoreExceptionMessage("book out of stock");
            }
        }
        else {
                throw new BookStoreExceptionMessage("book or user not present");
            }
        }

        public List<Cart> listAll () {
            if (cartRepository.findAll().isEmpty()) {
                throw new BookStoreExceptionMessage("No value present");
            } else
                return cartRepository.findAll();
        }


    @Override
    public Optional<Cart> getCartDetailsById(Integer cartId) {
        Optional<Cart> getCartData = cartRepository.findById(cartId);
        if (getCartData.isPresent()) {
            return getCartData;
        } else {
            throw new BookStoreExceptionMessage(" Id was not found");
        }
    }

    @Override
    public Optional<Cart> deleteCartItemById(Integer cartId) {
        Optional<Cart> cartItem = cartRepository.findById(cartId);
        if (cartItem.isPresent()) {
            cartRepository.deleteById(cartId);
            return cartItem;
        } else {
            throw new BookStoreExceptionMessage(" Id was not found ");
        }
    }

    @Override
    public Cart updateRecordById(Integer cartId, CartDto cartDTO) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        Optional<BookStore> book = bookRepository.findById(cartDTO.getBookId());
        Optional<UserDetail> user = userRepository.findById(cartDTO.getUserId());
        if (cart.isEmpty()) {
            throw new BookStoreExceptionMessage("Cart does not exist");
        } else {
            if (book.isPresent() && user.isPresent()) {
                Cart cart1 = new Cart( user.get(),book.get(), cartDTO.getQuantity(),cartDTO.price);
                cart1.setCartId(cartId);
                cartRepository.save(cart1);
                return cart1;
            } else {
                throw new BookStoreExceptionMessage("Book or User doesn't exists");
            }
        }
    }


    @Override
    public Cart updateQuantity(Integer cartId, Integer quantity) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isPresent()) {
            Optional<BookStore> bookStore = bookRepository.findById(cart.get().getBookStore().getBookId());

            if ((bookStore.get().getQuantity()) >=quantity) {
                bookStore.get().setQuantity(bookStore.get().getQuantity());
                cart.get().setQuantity(quantity);
                cartRepository.save(cart.get());
                bookRepository.save(bookStore.get());
                return cart.get();
            } else {
                throw new BookStoreExceptionMessage("book not in stock");
            }
        }
            else{
                throw new BookStoreExceptionMessage("book or user not present");
            }
        }
    }



