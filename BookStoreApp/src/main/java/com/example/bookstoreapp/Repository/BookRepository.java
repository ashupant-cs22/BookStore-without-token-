package com.example.bookstoreapp.Repository;


import com.example.bookstoreapp.Model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
      public interface BookRepository extends JpaRepository<BookStore,Integer> {


      @Query(value = "SELECT * FROM bookstore.book_store order by book_name",nativeQuery = true)
      List<BookStore> sortbyBookname();

      @Query(value = "SELECT * FROM bookstore.book_store order by price",nativeQuery = true)
      List<BookStore> sortbyBookPrice();

      @Query(value = "SELECT * FROM bookstore.book_store order by price desc",nativeQuery = true)
      List<BookStore> sortbyBookPriceHighToLow();

      @Query(value = "SELECT * FROM bookstore.book_store where book_name like 'L%'",nativeQuery = true)
      List<BookStore>findBookByName(String bookName);

}
