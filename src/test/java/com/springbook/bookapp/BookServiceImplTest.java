package com.springbook.bookapp;

import com.springbook.bookapp.entity.Book;
import com.springbook.bookapp.entity.BookDTO;
import com.springbook.bookapp.mapper.UniversalMapper;
import com.springbook.bookapp.repository.BookRepository;
import com.springbook.bookapp.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    private UniversalMapper universalMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        universalMapper = new UniversalMapper();
        bookService = new BookServiceImpl(bookRepository, universalMapper);
    }

    // findAll
    @Test
    void findAll_ReturnsBookDTOList_WhenBooksExist() {
        Book book1 = new Book("Title1", "Author1", 2020);
        Book book2 = new Book("Title2", "Author2", 2021);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        var result = bookService.findAll();

        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void findAll_ReturnsEmptyList_WhenNoBooksExist() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        var result = bookService.findAll();

        assertTrue(result.isEmpty());
        verify(bookRepository, times(1)).findAll();
    }

    // findById
    @Test
    void findById_ReturnsBook() {
        Book book = new Book("Test Book", "Test Author", 2022);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Book foundBook = bookService.findById(1);

        assertNotNull(foundBook);
        assertEquals("Test Book", foundBook.getTitle());
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    void findById_ThrowsException() {
        when(bookRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bookService.findById(99));
        verify(bookRepository, times(1)).findById(99);
    }

    // save
    @Test
    void save_SavesBookSuccessfully() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("New Book");
        bookDTO.setAuthor("Author X");
        bookDTO.setPublicationYear(2024);

        Book mappedBook = universalMapper.map(bookDTO, Book.class);
        mappedBook.setId(1);

        when(bookRepository.save(any(Book.class))).thenReturn(mappedBook);

        Book savedBook = bookService.save(bookDTO);

        assertNotNull(savedBook);
        assertEquals("New Book", savedBook.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }


    //  deleteById
    @Test
    void deleteById_DeletesBook_WhenIdExists() {
        when(bookRepository.existsById(1)).thenReturn(true);

        bookService.deleteById(1);

        verify(bookRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteById_ThrowsException_WhenIdDoesNotExist() {
        when(bookRepository.existsById(99)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> bookService.deleteById(99));
        verify(bookRepository, never()).deleteById(anyInt());
    }
}
