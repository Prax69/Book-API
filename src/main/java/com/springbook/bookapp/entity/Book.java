package com.springbook.bookapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Entity
@Data
@Table(name = "book")
public class Book {
    //    id, title, author, publicationYear.

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;


    private String title;


    private String author;


    private int publicationYear;

    public Book(){};

    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                '}';
    }
}
