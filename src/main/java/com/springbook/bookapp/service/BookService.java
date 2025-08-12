package com.springbook.bookapp.service;

import com.springbook.bookapp.entity.Book;
import com.springbook.bookapp.entity.BookDTO;
import java.util.List;

public interface BookService {

    // Get all books as DTOs
    List<BookDTO> findAll();

    // Find a book entity by ID
    Book findById(int id);

    // Save a book from DTO
    Book save(BookDTO theBookDTO);

    // Delete a book by ID
    void deleteById(int id);
}
