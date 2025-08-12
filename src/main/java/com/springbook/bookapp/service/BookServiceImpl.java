package com.springbook.bookapp.service;

import com.springbook.bookapp.entity.Book;
import com.springbook.bookapp.entity.BookDTO;
import com.springbook.bookapp.mapper.UniversalMapper;
import com.springbook.bookapp.repository.BookRepository;
import com.springbook.bookapp.mapper.UniversalMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UniversalMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, UniversalMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public List<BookDTO> findAll() {
        return mapper.mapList(bookRepository.findAll(), BookDTO.class);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public Book save(BookDTO theBookDTO) {
        Book book = mapper.map(theBookDTO, Book.class);
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
