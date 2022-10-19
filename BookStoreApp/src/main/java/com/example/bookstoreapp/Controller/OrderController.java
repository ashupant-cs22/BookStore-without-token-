package com.example.bookstoreapp.Controller;


import com.example.bookstoreapp.Dto.OrderDto;
import com.example.bookstoreapp.Dto.ResponseDTO;
import com.example.bookstoreapp.Model.Order;
import com.example.bookstoreapp.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    public IOrderService orderService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> add(@RequestBody OrderDto orderDto) {
        ResponseDTO dto = new ResponseDTO("Order placed ",orderService.addOrder(orderDto));
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<ResponseDTO> getAllOrderRecords() {
        List<Order> newOrder = orderService.getAllOrderRecords();
        ResponseDTO dto = new ResponseDTO("order retrieved successfully", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<ResponseDTO> deleteOrderRecord(@PathVariable Integer id){
        Order newOrder = orderService.deleteOrderRecord(id);
        ResponseDTO dto = new ResponseDTO("Record deleted successfully",newOrder);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }
    @GetMapping("/cancelOrder/{id}")
    public ResponseEntity<ResponseDTO> getCancelOrder(@PathVariable Integer id){
        Order deletedOrder = orderService.cancelOrder(id);
        ResponseDTO dto = new ResponseDTO("Cancel order successfully",deletedOrder);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

}
