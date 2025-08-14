package com.springbook.bookapp.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class BookDTO {
    private int id;
    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Author cannot be null")
    private String author;
    @NotNull(message = "Publication Year cannot be null")
    private int publicationYear;

    public BookDTO() {
    }
    // I have lombok annotations @Getter and @Setter

}
