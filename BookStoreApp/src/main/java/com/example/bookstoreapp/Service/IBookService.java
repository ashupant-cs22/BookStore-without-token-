package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.BookDto;
import com.example.bookstoreapp.Model.BookStore;
import java.util.List;

public interface IBookService {

    String getMessage();

    BookStore createBook(BookStore bookStore);

    List<BookStore> getListOfBook();

     BookStore getIdOfBook(int Id);

     void deleteBook(Integer id);

    List<BookStore> sortbyBookname();

    List<BookStore> sortbyBookPrice();

    List<BookStore> sortbyBookPriceHighToLow();

    BookStore updateById(int bookId, BookDto bookDto);

    BookStore updateBookQuantity(Integer id , int quantity);

    List<BookStore> getBookByName(String bookName);

 }
