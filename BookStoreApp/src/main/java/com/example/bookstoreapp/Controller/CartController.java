package com.example.bookstoreapp.Controller;

import com.example.bookstoreapp.Dto.CartDto;
import com.example.bookstoreapp.Dto.ResponseDTO;
import com.example.bookstoreapp.Model.Cart;
import com.example.bookstoreapp.Service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/add")
    public ResponseEntity <ResponseDTO> insertItem(@RequestBody CartDto cartDto){
        ResponseDTO responseDTO =new ResponseDTO("user added",cartService.insertItem(cartDto));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllCartDetails() {
        ResponseDTO responseDTO = new ResponseDTO("list",cartService.listAll());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable Integer cartId){
        ResponseDTO responseDTO=new ResponseDTO("cart details retrieved successfully",cartService.getCartDetailsById(cartId));
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable Integer cartId) {
        ResponseDTO responseDTO = new ResponseDTO("cart id " + cartId + " deleted", cartService.deleteCartItemById(cartId));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable Integer cartId, @RequestBody CartDto cartDTO){
        Cart updateRecord = cartService.updateRecordById(cartId,cartDTO);
        ResponseDTO dto = new ResponseDTO(" cart Record updated successfully by Id",updateRecord);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/updateCartQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable Integer id, @RequestParam Integer quantity) {
        Cart newCart = cartService.updateQuantity(id, quantity);
        ResponseDTO dto = new ResponseDTO("Quantity for book record updated successfully !", newCart);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

}

