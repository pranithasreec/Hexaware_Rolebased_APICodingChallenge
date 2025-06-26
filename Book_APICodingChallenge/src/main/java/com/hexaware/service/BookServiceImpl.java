package com.hexaware.service;

import com.hexaware.entity.Book;
import com.hexaware.exception.BookNotFoundException;
import com.hexaware.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return repository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public Book updateBook(Book book) {
        if (!repository.existsById(book.getIsbn())) {
            throw new BookNotFoundException("Book with ISBN " + book.getIsbn() + " not found");
        }
        return repository.save(book);
    }
    public Book updateBookByIsbn(String isbn, Book book) {
        Book existing = repository.findById(isbn)
            .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found"));

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setPublicationYear(book.getPublicationYear());

        return repository.save(existing);
    }

    public void deleteBook(String isbn) {
        if (!repository.existsById(isbn)) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found");
        }
        repository.deleteById(isbn);
    }
}
