package com.springbook.bookapp.service;

import com.springbook.bookapp.entity.Book;
import com.springbook.bookapp.entity.BookDTO;
import com.springbook.bookapp.repository.BookRepository;
import com.springbook.bookapp.entity.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public Book save(BookDTO theBookDTO) {
        Book book = BookMapper.toEntity(theBookDTO);
        return bookRepository.save(book);
    }

    @Override
    public void deleteById(int id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
