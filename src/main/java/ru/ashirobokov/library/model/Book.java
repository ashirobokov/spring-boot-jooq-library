package ru.ashirobokov.library.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book {
    private Long id;
    private String title;

    public Book(String title) {
        this.title = title;
    }
}
