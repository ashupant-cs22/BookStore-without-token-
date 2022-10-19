package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.OrderDto;
import com.example.bookstoreapp.Model.Order;
import java.util.List;

public interface IOrderService {

    Order addOrder(OrderDto orderDto);

    List<Order> getAllOrderRecords();

    Order deleteOrderRecord(Integer id);

    Order cancelOrder(Integer id);
}
