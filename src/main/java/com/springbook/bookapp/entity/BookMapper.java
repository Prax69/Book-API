// redundant and should be ignored
// This file is intentionally left untouched for learning purposes

package com.springbook.bookapp.entity;

import com.springbook.bookapp.mapper.UniversalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {


    @Autowired
    private static  UniversalMapper universalMapper ;

    public BookMapper(UniversalMapper universalMapper) {
        BookMapper.universalMapper = universalMapper;
    }

    public static BookDTO toDTO(Book book) {
        return universalMapper.map(book, BookDTO.class);
    }

    public static Book toEntity(BookDTO bookDTO) {
        return universalMapper.map(bookDTO, Book.class);
    }
}
