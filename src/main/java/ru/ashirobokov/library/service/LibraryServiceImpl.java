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
        log.info(".... LibraryService.saveAuthor .. {}", author);
        Author savedAuthor = libraryRepository.insertAuthor(author);

        if (savedAuthor != null) {
            // also some processing can be done here
            log.info(".... LibraryService.saveAuthor .. savedAuthor {}", savedAuthor);
        } else {
            log.error(".... ERROR LibraryService.saveAuthor .. saved Author value is null");
        }

        return savedAuthor;
    }

    @Override
    public Long removeAuthor(Long id) {
        log.info("LibraryService/removeAuthor id = {}", id);

        Long authorId = libraryRepository.deleteAuthor(id);
        if (authorId < 0) {
            log.error("ERROR LibraryService/removeAuthor error ");
            return -1L;
        }

        return authorId;
    }

    @Override
    public List<Author> findAllAuthors() {
        log.info(".... LibraryService.findAllAuthors");
        List<Author> authors = libraryRepository.selectAllAuthors();

        if (null == authors) {
            log.error(".... ERROR LibraryService.findAllAuthors .. Author's list is null");
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
    public Author findAuthorByLastName(String lastName) {
        log.info("LibraryService/findAuthorByLastName last name = {}", lastName);
        Author author = libraryRepository.selectAuthorByLastName(lastName);

        if (author == null) {
            log.error("LibraryService/findAuthorByLastName .. ERROR when searching for the author with last name = {}", lastName);
        }

        return author;
    }

    @Override
    public Author findAuthorById(Long authorId) {
        log.info("LibraryService/findAuthorById id = {}", authorId);
        Author author = libraryRepository.selectAuthorById(authorId);

        if (author == null) {
            log.error("LibraryService/findAuthorById .. ERROR when searching for the author with id = {}", authorId);
        }

        return author;
    }

    @Override
    public Book saveBook(String authorName, String title) {
        log.info("LibraryService/saveBook author={}, title={}", authorName, title);
        Author author = findAuthorByLastName(authorName);

        if (author == null) {
            log.error("LibraryService/saveBook .. ERROR Author not found");
            return null;
        }

        Book book = libraryRepository.insertBook(author, title);

        if (book == null) {
            log.error("LibraryService/saveBook .. ERROR to save Book with title={}", title);
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
    public Book findBookByTitle(String title) {
        log.info("LibraryService/findBookByTitle title = \"{}\"", title);
        Book book = libraryRepository.selectBookByTitle(title);

        if (book == null) {
            log.error("ERROR LibraryService/findBookByTitle .. book with title \"{}\" not found", title);
        }

        return book;
    }

    @Override
    public Book findBookById(Long id) {
        log.info("LibraryService/findBookById id = {}", id);
        Book book = libraryRepository.selectBookById(id);

        if (book == null) {
            log.error("ERROR LibraryService/findBookById .. book with id = {} not found", id);
        }

        return book;
    }

    @Override
    public AuthorBookList findBooksByAuthor(String lastName) {
        log.info(".... LibraryService.findBooksByAuthor .. lastName = \"{}\"", lastName);
        Author author = libraryRepository.selectAuthorByLastName(lastName);
        if (null == author) {
            log.error(" ERROR: LibraryService.findBooksByAuthor : не найден писатель с фамилией \"{}\"", lastName);
            return null;
        }

        List<Book> books = new ArrayList<>();
        books = libraryRepository.selectBooksByAuthor(author);
        log.info("Список книг для автора {} = {}", lastName, books);

        if (null == books) {
            log.error(" ERROR: LibraryService.findBooksByAuthor : не найден список книг для писателя с фамилией \"{}\"", lastName);
            return null;
        }

        return new AuthorBookList(author.getLastName(), books);
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
