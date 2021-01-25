package ru.ashirobokov.library.service;

import ru.ashirobokov.library.model.Author;
import ru.ashirobokov.library.model.AuthorBookList;
import ru.ashirobokov.library.model.Book;

import java.util.List;

public interface LibraryService {

    public Author saveAuthor(Author author);

    public Long removeAuthor(Long id);

    public List<Author> findAllAuthors();

    public Author findAuthorByLastName(String lastName);

    public Author findAuthorById(Long id);

    public Long countAuthors();

    public Book saveBook(String authorName, String title);

    public Long removeBook(Long id);

    public Book findBookByTitle(String title);

    public Book findBookById(Long id);

    public AuthorBookList findBooksByAuthor(String lastName);

    public Long countBooks();



}
