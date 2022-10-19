package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.Dto.BookDto;
import com.example.bookstoreapp.Exception.BookStoreExceptionMessage;
import com.example.bookstoreapp.Model.BookStore;
import com.example.bookstoreapp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository bookRepository;

    public String getMessage() {

        return "Hello welcome to Book Store";
    }


    @Override
    public BookStore createBook(BookStore bookStore) {
      return bookRepository.save(bookStore);
    }

    @Override
    public List<BookStore> getListOfBook(){
        List<BookStore>bookStores=bookRepository.findAll();
        return bookStores;
    }

    @Override
    public BookStore getIdOfBook(int Id) {
        return bookRepository.findById(Id)
                .orElseThrow(() -> new BookStoreExceptionMessage("Book with Id" + Id + "  not found"));
    }
    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
    @Override
    public BookStore updateById(int bookId, BookDto bookDto) {
            BookStore bookStore = new BookStore(bookDto);
            bookStore.setBookId(bookId);
            bookRepository.save(bookStore);
            return bookStore;
        }

    @Override
    public List<BookStore> sortbyBookname() {
        List<BookStore>book=bookRepository.sortbyBookname();
        return book;

    }
    @Override
    public List<BookStore> sortbyBookPrice() {
        List<BookStore>book=bookRepository.sortbyBookPrice();
        return book;
    }

    @Override
    public List<BookStore> sortbyBookPriceHighToLow() {
        List<BookStore>book= bookRepository.sortbyBookPriceHighToLow();
        return book;

    }

    @Override
    public BookStore updateBookQuantity(Integer id , int quantity) {
      Optional<BookStore> bookStore=bookRepository.findById(id);
      if(bookStore.isPresent()){
          bookStore.get().setQuantity(quantity);
          bookRepository.save(bookStore.get());
          return bookStore.get();
      }else {
          throw new BookStoreExceptionMessage("Book not found");
      }
    }


    @Override
    public List<BookStore> getBookByName(String bookName) {
        return bookRepository.findBookByName(bookName);
    }
}
