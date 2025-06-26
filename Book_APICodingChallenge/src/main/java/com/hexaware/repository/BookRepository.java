package com.hexaware.repository;
import com.hexaware.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, String>{

}
