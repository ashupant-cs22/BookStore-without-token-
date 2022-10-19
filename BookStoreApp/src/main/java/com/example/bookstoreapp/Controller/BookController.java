package com.example.bookstoreapp.Controller;

import com.example.bookstoreapp.Dto.BookDto;
import com.example.bookstoreapp.Dto.ResponseDTO;
import com.example.bookstoreapp.Model.BookStore;
import com.example.bookstoreapp.Service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/book")
public class BookController {


@Autowired
    IBookService bookService;

    @GetMapping("/hello")
    public String hello() {
        String msg = bookService.getMessage();
        return msg;
    }


    @PostMapping("/add")
    public ResponseEntity<ResponseDTO>createbook(@RequestBody BookDto bookDto){
        BookStore bookStore=new BookStore(bookDto);
        ResponseDTO responseDTO=new ResponseDTO("book has been added",bookService.createBook(bookStore));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllData() {
        List<BookStore> bookStores = bookService.getListOfBook();
        ResponseDTO response = new ResponseDTO("List Of Book:", bookStores);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
        BookStore bookStore= bookService.getIdOfBook(id);
        ResponseDTO response = new ResponseDTO("Book of given id: ", bookStore);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDataById(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return new ResponseEntity<String>("Book deleted successfully", HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable Integer id, @RequestBody@Valid BookDto BookDTO) {
        BookStore newContact = bookService.updateById(id, BookDTO);
        ResponseDTO response = new ResponseDTO("book updated : ", newContact);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }


    @GetMapping(value = {"/sortByName"})
    public ResponseEntity<ResponseDTO> sortByBookName() {
        List<BookStore> BookList = bookService.sortbyBookname();
        ResponseDTO responseDTO = new ResponseDTO(" sorted by Book Name ", BookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping(value = {"/sortByPriceLowToHigh"})
    public ResponseEntity<ResponseDTO> sortByBookPrice() {
        List<BookStore> BookList = bookService.sortbyBookPrice();
        ResponseDTO responseDTO = new ResponseDTO(" sorted by Book Price ", BookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = {"/sortByPriceHighToLow"})
    public ResponseEntity<ResponseDTO> sortByBookPriceHighToLow() {
        List<BookStore> BookList = bookService.sortbyBookPriceHighToLow();
        ResponseDTO responseDTO = new ResponseDTO(" sorted by Book Price ", BookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable int id, @RequestParam int quantity) {
        BookStore bookStore = bookService.updateBookQuantity(id, quantity);
        ResponseDTO response = new ResponseDTO("book updated : ", bookStore);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    @GetMapping("/bookName/{bookName}")
    public ResponseEntity<ResponseDTO> getbookByName(@PathVariable String bookName){
        List<BookStore> getBookByName=bookService.getBookByName(bookName);
        ResponseDTO responseDTO=new ResponseDTO(" books By name ",getBookByName);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }
}

