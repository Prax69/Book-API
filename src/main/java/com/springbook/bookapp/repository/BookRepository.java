package com.springbook.bookapp.repository;


import com.springbook.bookapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // No additional methods are needed as JpaRepository provides basic CRUD operations
    public List<Book> findAllByOrderByTitleAsc();
}
