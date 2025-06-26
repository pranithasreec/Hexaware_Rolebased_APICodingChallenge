package com.hexaware.service;

import com.hexaware.entity.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookByIsbn(String isbn);
    Book addBook(Book book);
    Book updateBook(Book book);
    void deleteBook(String isbn);
    Book updateBookByIsbn(String isbn, Book book);


}
