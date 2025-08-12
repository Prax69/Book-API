package com.springbook.bookapp.controller;

import com.springbook.bookapp.entity.Book;
import com.springbook.bookapp.entity.BookDTO;
import com.springbook.bookapp.exceptions.BookNotFoundException;
import com.springbook.bookapp.service.BookService;
import com.springbook.bookapp.entity.BookMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService theBookService) {
        this.bookService = theBookService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Book API!";
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> bookList = bookService.findAll();
        if (bookList.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(bookList);
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO bookDTO) {
//        if ((bookDTO.getTitle()).isEmpty()  ||
//                bookDTO.getAuthor().isEmpty() ||
//                bookDTO.getPublicationYear() <= 1800 ||
//                bookDTO.getPublicationYear() > 2025
//        ) {
//            return ResponseEntity.badRequest().body("Invalid book data provided");
//        }
        bookService.save(bookDTO);
        return ResponseEntity.status(201).body("Book added successfully");
    }

    // Delete a book by ID
    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        Book book = bookService.findById(bookId);
        if (book == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        bookService.deleteById(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }

    // Update a book by ID
    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable int bookId, @RequestBody BookDTO bookDTO) {
        Book existingBook = bookService.findById(bookId);
        if (existingBook == null) {
            throw new BookNotFoundException("Book ID not found - " + bookId);
        }

        // Update entity from DTO
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuthor(bookDTO.getAuthor());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());

        // Save updated book
        bookService.save(BookMapper.toDTO(existingBook));
        return ResponseEntity.ok("Book updated successfully");
    }

    // Get a book by ID
    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int bookId) {
        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book ID not found - " + bookId);
        }
        return ResponseEntity.ok(BookMapper.toDTO(book));
    }
}
