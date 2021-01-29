package ru.ashirobokov.library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ashirobokov.library.model.Author;
import ru.ashirobokov.library.model.AuthorBookList;
import ru.ashirobokov.library.model.Book;
import ru.ashirobokov.library.repository.LibraryRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public Author saveAuthor(Author author) {
        log.info("LibraryService/saveAuthor .. Author {} {}", author.getFirstName(), author.getLastName());
        Author savedAuthor = libraryRepository.insertAuthor(author);

        if (savedAuthor == null) {
            log.error("LibraryService/saveAuthor .. ERROR to save Author {} {}", author.getFirstName(), author.getLastName());
        }

        return savedAuthor;
    }

    @Override
    public Long removeAuthor(Long id) {
        log.info("LibraryService/removeAuthor .. id = {}", id);

        Long authorId = libraryRepository.deleteAuthor(id);
        if (authorId < 0) {
            log.error("ERROR LibraryService/removeAuthor .. ERROR when removing Author with id = {}", id);
            return -1L;
        }

        return authorId;
    }

    @Override
    public List<Author> findAllAuthors() {
        log.info("LibraryService/findAllAuthors");
        List<Author> authors = libraryRepository.selectAllAuthors();

        if (null == authors) {
            log.error("LibraryService/findAllAuthors .. ERROR ");
        }

        return authors;
    }

    @Override
    public Long countAuthors() {
        log.info("LibraryService/countAuthors");
        Long number = libraryRepository.countAuthors();

        if (number == null) {
            log.error("LibraryService/countAuthors .. ERROR when calculating the number of writers");
        }

        return number;
    }

    @Override
    public List<Author> findAuthorByLastName(String lastName) {
        log.info("LibraryService/findAuthorByLastName .. last name = {}", lastName);
        List<Author> authors = libraryRepository.selectAuthorByLastName(lastName);

        if (authors == null) {
            log.error("LibraryService/findAuthorByLastName .. ERROR when searching for the author with last name = {}", lastName);
        }

        return authors;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        log.info("LibraryService/findAuthorById .. id = {}", authorId);
        Author author = libraryRepository.selectAuthorById(authorId);

        if (author == null) {
            log.error("LibraryService/findAuthorById .. ERROR when searching for the author with id = {}", authorId);
        }

        return author;
    }

    @Override
    public Book saveBook(Long authorId, String title) {
        log.info("LibraryService/saveBook .. author id={}, title={}", authorId, title);
        Author author = findAuthorById(authorId);

        if (author == null) {
            log.error("LibraryService/saveBook .. ERROR Author id = {} not found", authorId);
            return null;
        }

        Book book = libraryRepository.insertBook(author, title);

        if (book == null) {
            log.error("LibraryService/saveBook .. ERROR to save Book with title '{}'", title);
        }

        return book;
    }

    @Override
    public Long removeBook(Long id) {
        log.info("LibraryService/removeBook with id = {}", id);

        Long bookId = libraryRepository.deleteBook(id);
        if (bookId < 0) {
            log.error("ERROR LibraryService/removeBook error ");
            return -1L;
        }

        return bookId;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        log.info("LibraryService/findBookByTitle .. title = {}", title);
        List<Book> books = libraryRepository.selectBookByTitle(title);

        if (books == null) {
            log.error("ERROR LibraryService/findBookByTitle .. book with title '{}' not found", title);
        }

        return books;
    }

    @Override
    public Book findBookById(Long id) {
        log.info("LibraryService/findBookById .. id = {}", id);
        Book book = libraryRepository.selectBookById(id);

        if (book == null) {
            log.error("ERROR LibraryService/findBookById .. book with id = {} not found", id);
        }

        return book;
    }

    @Override
    public AuthorBookList findBooksByAuthor(Long authorId) {
        log.info("LibraryService/findBooksByAuthor .. Author id = {}", authorId);
        Author author = libraryRepository.selectAuthorById(authorId);

        if (null == author) {
            log.error(" ERROR LibraryService/findBooksByAuthor : Author id = {} not found", authorId);
            return null;
        }

        List<Book> books = new ArrayList<>();
        books = libraryRepository.selectBooksByAuthor(author);
        log.info("Список книг для автора {} = {}", authorId, books);

        if (null == books) {
            log.error("ERROR LibraryService/findBooksByAuthor .. book list of Author id = {} not found", authorId);
            return null;
        }

        return new AuthorBookList(author.getId(), author.getLastName(), books);
    }

    @Override
    public Long countBooks() {
        log.info("LibraryService/countBooks");
        Long quantity = libraryRepository.countBooks();

        if (quantity == null) {
            log.error("LibraryService/countBooks .. ERROR when calculating the quantity of books");
        }

        return quantity;
    }

}
