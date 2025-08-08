package com.springbook.bookapp.controller;


import com.springbook.bookapp.entity.Book;
import com.springbook.bookapp.exceptions.BookNotFoundException;
import com.springbook.bookapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService theBookService) {
        bookService = theBookService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Book API!";
    }

    //Get all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> books() {
        List<Book> bookList = bookService.findAll();

        //appropriate HttpCode
        if (bookList.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(bookList);
    }

    // Post a book
    @PostMapping("/books")
    public ResponseEntity<String> bookAdder(@RequestBody Book book) {
        if (book.getTitle() == null || book.getAuthor() == null || book.getPublicationYear() <= 1800 || book.getPublicationYear() > 2025) {
            return ResponseEntity.badRequest().body("Invalid book data provided");
        }
        bookService.save(book);
        return ResponseEntity.status(201).body("Book added successfully");
    }

    // Delete a book by ID
    @PostMapping("/books/{bookId}")
    public ResponseEntity<String> bookDeleter(@PathVariable int bookId) {
        Book book = bookService.findById(bookId);

        if (book == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        bookService.deleteById(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

    // Update a book by ID
    @PutMapping("/books/{bookId}")
    public void bookUpdater(@PathVariable int bookId, @RequestBody Book book) {
        Book oldBook = bookService.findById(bookId);
        if (oldBook == null) {
            throw new RuntimeException("Book ID not found - " + bookId);
        }
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPublicationYear(book.getPublicationYear());
        bookService.save(oldBook);
    }

    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        Book book = bookService.findById(bookId);
        if (book == null) {
            System.out.println("hello");
            throw new BookNotFoundException("Book ID not found - " + bookId);
        }
        return book;
    }
}
