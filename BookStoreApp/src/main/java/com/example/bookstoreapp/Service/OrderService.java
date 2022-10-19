package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.OrderDto;
import com.example.bookstoreapp.Exception.BookStoreExceptionMessage;
import com.example.bookstoreapp.Model.BookStore;
import com.example.bookstoreapp.Model.Cart;
import com.example.bookstoreapp.Model.Order;
import com.example.bookstoreapp.Model.UserDetail;
import com.example.bookstoreapp.Repository.BookRepository;
import com.example.bookstoreapp.Repository.CartRepository;
import com.example.bookstoreapp.Repository.OrderRepository;
import com.example.bookstoreapp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {


    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public CartService cartService;


    public Order addOrder(OrderDto orderDto) {
        Optional<UserDetail> userDetail = userRepository.findById(orderDto.getUserId());
        List<Cart> cart = cartRepository.getByUserId(orderDto.getUserId());
        int totalOrderPrice = 0;
        int totalOrderQty = 0;
        List<BookStore> orderedBooks = new ArrayList<>();
        if (cart != null && userDetail.isPresent()) {
            for (int i = 0; i < cart.size(); i++) {
                totalOrderPrice += cart.get(i).getPrice();
                totalOrderQty += cart.get(i).getQuantity();
                orderedBooks.add(cart.get(i).getBookStore());
            }
            Order order = new Order(userDetail.get().getUserId(), orderDto.getAddress(), cart, orderedBooks, LocalDate.now(), totalOrderQty, totalOrderPrice, false);
            return orderRepository.save(order);
        }
            else {
            throw new BookStoreExceptionMessage("cart id not present");
        }

    }


    public List<Order> getAllOrderRecords() {
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }


    public Order deleteOrderRecord(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            orderRepository.deleteById(id);
            return order.get();
        } else {
            throw new BookStoreExceptionMessage("no record found");
        }
    }

    public Order cancelOrder(Integer id) {
//        Optional<Order> order = orderRepository.findById(id);
//        if (order.isPresent()) {
//            order.get().setCancel(true);
//            BookStore book = order.get().cart.ge();
//            book.setQuantity(book.getQuantity() + order.get().getQuantity());
//            bookRepository.save(book);
//            orderRepository.deleteById(id);
//            return order.get();
//
//        } else {
//            throw new BookStoreExceptionMessage("Order Record doesn't exists");
//        }
        return null;
    }
}



