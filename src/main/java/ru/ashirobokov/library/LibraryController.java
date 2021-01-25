package ru.ashirobokov.library;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ashirobokov.library.model.Author;
import ru.ashirobokov.library.model.AuthorBook;
import ru.ashirobokov.library.model.AuthorBookList;
import ru.ashirobokov.library.model.Book;
import ru.ashirobokov.library.response.Response;
import ru.ashirobokov.library.service.LibraryService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping(value = "test1")
    public ResponseEntity<String> test1() {
        log.info(".. LibraryController.test1");
        return ResponseEntity.ok("test1, test1!");
    }

    @RequestMapping(value = "test2", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response test2() {
        log.info(".. LibraryController.test2");
        return new Response("test2, test2!");
    }

    @RequestMapping(value = "authors", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response createAuthor(@RequestBody Author author) {
        log.info("LibraryController/createAuthor .. {}", author);
        Response response = new Response();

        Author savedAuthor = libraryService.saveAuthor(author);
        if (null != savedAuthor) {
            response.setData(savedAuthor);
        } else {
            response.setError("Error : Ошибка при попытке внести данные писателя " + author + " в БД");
        }

        return response;
    }

    @RequestMapping(value = "author/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response removeAuthor(@PathVariable("id") Long id) {
        log.info("LibraryController/removeAuthor id = {}", id);
        Response response = new Response();

        Long authorId = libraryService.removeAuthor(id);
        if (authorId >= 0) {
            response.setData(authorId);
        } else {
            response.setError("Error : Ошибка при попытке удалить данные писателя из БД ");
        }

        return response;
    }

    @RequestMapping(value = "authors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
      public Response getAllAuthors() {
        log.info("LibraryController/getAllAuthors");
        Response response = new Response();
        List<Author> authors = libraryService.findAllAuthors();
        if (null != authors) {
            response.setData(authors);
        } else {
            response.setError("Error : Ошибка при попытке получить список писателей из БД ");
        }

        return response;
    }

    @RequestMapping(value = "authors/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response countAllAuthors() {
        log.info("LibraryController/countAllAuthors");
        Response response = new Response();
        Long result = libraryService.countAuthors();

        if (result != null) {
            response.setData(result);
        } else {
            response.setError("Error : Ошибка при подсчете количества писателей в БД ");
        }

        return response;
    }

    @RequestMapping(value = "/author/{last_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findAuthorByLastName(@PathVariable("last_name") String lastName) {
        log.info("LibraryController/findAuthorByLastName .. last name = {}", lastName);
        Response response = new Response();

        Author returnedAuthor = libraryService.findAuthorByLastName(lastName);
        if (null != returnedAuthor) {
            response.setData(returnedAuthor);
        } else {
            response.setError("Error : Ошибка при поиске данных писателя '" + lastName + "' в БД");
        }

        return response;
    }

    @RequestMapping(value = "/author/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findAuthorById(@PathVariable("id") Long authorId) {
        log.info("LibraryController/findAuthorById .. id = {}", authorId);
        Response response = new Response();

        Author returnedAuthor = libraryService.findAuthorById(authorId);
        if (returnedAuthor != null) {
            response.setData(returnedAuthor);
        } else {
            response.setError("Error : Ошибка при поиске данных писателя '" + authorId + "' в БД");
        }

        return response;
    }

    @RequestMapping(value = "book", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addBook(@RequestBody AuthorBook authorNameBookTitle) {
        log.info("LibraryController/addBook title = {}", authorNameBookTitle);
        Response response = new Response();

        Book savedBook = libraryService.saveBook(authorNameBookTitle.getAuthorName(), authorNameBookTitle.getBookTitle());

        if (null != savedBook) {
            response.setData(savedBook);
        } else {
            response.setError("Error : Ошибка сохранения книги '" + authorNameBookTitle.getBookTitle() + "' в БД библиотеки");
        }

        return response;
    }

    /**
     * Removing a book by Id
     * @param id
     * @return Response object
     */
    @RequestMapping(value = "book/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response removeBook(@PathVariable("id") Long id) {
        log.info("LibraryController/removeBook id = {}", id);
        Response response = new Response();

        Long bookId = libraryService.removeBook(id);
        if (bookId >= 0) {
            response.setData(bookId);
        } else {
            response.setError("Error : Ошибка при попытке удалить книгу id = " + id + " из БД");
        }

        return response;
    }


    /**
     * Searching a book by its Title
     * @param title
     * @return Response object
     */
    @RequestMapping(value = "/book/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findBookByTitle(@PathVariable("title") String title) {
        log.info("LibraryController/findBooksByTitle .. title \"{}\"", title);
        Response response = new Response();

        Book returnedBook = libraryService.findBookByTitle(title);
        if (null != returnedBook) {
            response.setData(returnedBook);
        } else {
            response.setError("Error : Ошибка поиска книги с названием '" + title + "' в БД ");
        }

        return response;
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findBookById(@PathVariable("id") Long id) {
        log.info("LibraryController/findBooksById .. id = {}", id);
        Response response = new Response();

        Book returnedBook = libraryService.findBookById(id);
        if (null != returnedBook) {
            response.setData(returnedBook);
        } else {
            response.setError("Error : Ошибка поиска книги с идентификатором " + id + " в БД ");
        }

        return response;
    }

    @RequestMapping(value = "/books/{last_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findBooksByAuthor(@PathVariable("last_name") String lastName) {
        log.info(".... LibraryController.findBooksByAuthor .. {}", lastName);
        Response response = new Response();

        AuthorBookList authorBookList = libraryService.findBooksByAuthor(lastName);
        if (null != authorBookList) {
            response.setData(authorBookList);
        } else {
            response.setError("Error : Ошибка при попытке получить список книг писателя '" + lastName + "' из БД ");
        }

        return response;
    }

    @RequestMapping(value = "books/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response countAllBooks() {
        log.info("LibraryController/countAllBooks");
        Response response = new Response();
        Long result = libraryService.countBooks();

        if (result != null) {
            response.setData(result);
        } else {
            response.setError("Error : Ошибка при общем подсчете количества книг в БД ");
        }

        return response;
    }


/* TODO
    Remove these commented methods into archive
 */
/*
    @RequestMapping(value = "authors", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Author> getAllAuthors() {
        log.info(".. LibraryController.getAllAuthors");

        return libraryService.findAllAuthors();
    }
*/

/*
    @RequestMapping(value = "author", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Author createAuthor(@RequestBody Author author) {
        log.info(".... LibraryController.createAuthor .. {}", author);
        return libraryService.saveAuthor(author);
    }
*/

}
