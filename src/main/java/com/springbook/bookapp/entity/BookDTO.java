package com.springbook.bookapp.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private int publicationYear;

    public BookDTO() {}
   // I have lombok annotations @Getter and @Setter

}
