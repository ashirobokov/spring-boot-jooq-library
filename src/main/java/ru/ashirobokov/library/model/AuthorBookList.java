package ru.ashirobokov.library.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AuthorBookList {
    Long authorId;
    String authorName;
    List<Book> books;
}
