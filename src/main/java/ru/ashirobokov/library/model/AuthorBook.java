package ru.ashirobokov.library.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class AuthorBook {
    Long authorId;
    String authorName;
    String bookTitle;
}
