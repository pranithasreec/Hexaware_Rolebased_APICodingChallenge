package com.hexaware.controller;

import com.hexaware.entity.Book;
import com.hexaware.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }
    @PutMapping("/update/{isbn}")
    public Book updateBookByIsbn(@PathVariable String isbn, @RequestBody Book book) {
        return bookService.updateBookByIsbn(isbn, book);
    }


    @DeleteMapping("/delete/{isbn}")
    public String deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return "Book deleted successfully";
    }
}
