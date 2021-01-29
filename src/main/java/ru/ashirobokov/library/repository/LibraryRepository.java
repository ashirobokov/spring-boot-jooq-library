package ru.ashirobokov.library.repository;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ashirobokov.library.jooq.tables.AuthorsBooks;
import ru.ashirobokov.library.jooq.tables.records.AuthorsBooksRecord;
import ru.ashirobokov.library.model.Author;
import ru.ashirobokov.library.model.Book;

import java.util.List;

import static ru.ashirobokov.library.jooq.Tables.AUTHORS_BOOKS;
import static ru.ashirobokov.library.jooq.tables.Authors.AUTHORS;
import static ru.ashirobokov.library.jooq.tables.Books.BOOKS;


@Slf4j
@Repository
public class LibraryRepository {

    @Autowired
    private DSLContext dsl;

    public List<Author> selectAllAuthors() {
        try {
            log.info("LibraryRepository/selectAllAuthors");

            return dsl.selectFrom(AUTHORS)
                    .fetchInto(Author.class);
        } catch (Exception e) {
            log.error("LibraryRepository/selectAllAuthors error", e);
            return null;
        }
    }

    public Author insertAuthor(Author author) {
        try {
            log.info("LibraryRepository/insert .. Author {} {}", author.getFirstName(), author.getLastName());

            return dsl.insertInto(AUTHORS)
                    .set(AUTHORS.FIRST_NAME, author.getFirstName())
                    .set(AUTHORS.LAST_NAME, author.getLastName())
                    .returning()
                    .fetchOne()
                    .into(Author.class);
        } catch (Exception e) {
            log.error("LibraryRepository/insertAuthor error", e);
            return null;
        }
    }

    @Transactional
    public Long deleteAuthor(Long authorId) {
        try {
            log.info("LibraryRepository/delete .. Author id = {}", authorId);
/*
                Result<Record1<Long>> result = dsl.select(AUTHORS_BOOKS.BOOK_ID)
                        .from(AUTHORS_BOOKS)
                        .where(AUTHORS_BOOKS.AUTHOR_ID.eq(author.getId()))
                        .fetch();

                result.forEach(lr -> {
                             dsl.deleteFrom(BOOKS)
                                .where(BOOKS.ID.eq(lr.value1()))
                                .execute();
                        }
                );
*/
                Result<AuthorsBooksRecord> result = dsl.selectFrom(AUTHORS_BOOKS)
                        .where(AUTHORS_BOOKS.AUTHOR_ID.eq(authorId))
                        .fetch();

                result.forEach(r -> {
                    dsl.deleteFrom(BOOKS)
                            .where(BOOKS.ID.eq(r.getBookId()))
                            .execute();
                });

                dsl.deleteFrom(AUTHORS_BOOKS)
                        .where(AUTHORS_BOOKS.AUTHOR_ID.eq(authorId))
                        .execute();

                dsl.deleteFrom(AUTHORS)
                        .where(AUTHORS.ID.eq(authorId))
                        .execute();

            return authorId;

        } catch (Exception e) {
            log.error("LibraryRepository/deleteAuthor error", e);
            return -1L;
        }
    }

    public Author selectAuthorById(Long authorId) {
        try {
            log.info("LibraryRepository/selectAuthorById .. id = {}", authorId);
            return dsl.selectFrom(AUTHORS)
                    .where(AUTHORS.ID.eq(authorId))
                    .fetchOneInto(Author.class);
        } catch (Exception e) {
            log.error("LibraryRepository/selectAuthorById error", e);
            return null;
        }
    }

    public List<Author> selectAuthorByLastName(String lastName) {
        try {
            log.info("LibraryRepository/selectAuthorByLastName .. lastName = {}", lastName);

            return dsl.selectFrom(AUTHORS)
                    .where(AUTHORS.LAST_NAME.eq(lastName))
                    .fetchInto(Author.class);
        } catch (Exception e) {
            log.error("LibraryRepository/selectAuthorByLastName error", e);
            return null;
        }
    }

    public Long countAuthors() {
         try {
            log.info("LibraryRepository/countAuthors");
            return dsl.selectCount()
                    .from(AUTHORS)
                    .fetchOneInto(Long.class);
        } catch (Exception e) {
            log.error("LibraryRepository/countAuthors error", e);
            return null;
        }
    }

    @Transactional
    public Book insertBook(Author author, String title) {
        try {
            log.info("LibraryRepository/insertBook .. Author id = {}, Author name = {}, title = {}", author.getId(), author.getLastName(), title);

            Long bookId = dsl.insertInto(BOOKS)
                    .set(BOOKS.TITLE, title)
                    .returning(BOOKS.ID)
                    .fetchOne().getId();

            dsl.insertInto(AuthorsBooks.AUTHORS_BOOKS)
                    .set(AuthorsBooks.AUTHORS_BOOKS.AUTHOR_ID, author.getId())
                    .set(AuthorsBooks.AUTHORS_BOOKS.BOOK_ID, bookId)
                    .execute();

            return selectBookById(bookId);
        } catch (Exception e) {
            log.error("LibraryRepository/insertBook error", e);
            return null;
        }
    }

    @Transactional
    public Long deleteBook(Long bookId) {
        try {
            log.info("LibraryRepository/deleteBook .. id = {}", bookId);

            dsl.deleteFrom(AUTHORS_BOOKS)
                    .where(AUTHORS_BOOKS.BOOK_ID.eq(bookId))
                    .execute();

            int deletedRows = dsl.deleteFrom(BOOKS)
                    .where(BOOKS.ID.eq(bookId))
                    .execute();

            if (deletedRows > 0) {
                return bookId;
            } else {
                return -1L;
            }

        } catch (Exception e) {
            log.error("LibraryRepository/deleteBook error", e);
            return -1L;
        }
    }

    public List<Book> selectBookByTitle(String title) {
        try {
            log.info("LibraryRepository/selectBookByTitle .. title = {}", title);

            return dsl.selectFrom(BOOKS)
                    .where(BOOKS.TITLE.eq(title))
                    .fetchInto(Book.class);
        } catch (Exception e) {
            log.error("LibraryRepository/selectBookByTitle error", e);
            return null;
        }
    }

    public Book selectBookById(Long id) {
        try {
            log.info("LibraryRepository/selectBookById .. id = {}", id);

            return dsl.selectFrom(BOOKS)
                    .where(BOOKS.ID.eq(id))
                    .fetchOneInto(Book.class);
        } catch (Exception e) {
            log.error("LibraryRepository/selectBookById error", e);
            return null;
        }
    }

    public List<Book> selectBooksByAuthor(Author author) {
        try {
            log.info("LibraryRepository/selectBooksByAuthor .. Author id = {}, last name = {}", author.getId(), author.getLastName());
            return dsl.select(BOOKS.ID,
                              BOOKS.TITLE)
                         .from(BOOKS)
                         .leftJoin(AuthorsBooks.AUTHORS_BOOKS).on(AuthorsBooks.AUTHORS_BOOKS.BOOK_ID.eq(BOOKS.ID))
                         .leftJoin(AUTHORS).on(AUTHORS.ID.eq(AuthorsBooks.AUTHORS_BOOKS.AUTHOR_ID))
                         .where(AUTHORS.ID.eq(author.getId()))
                         .orderBy(BOOKS.ID)
                         .fetchInto(Book.class);
        } catch (Exception e) {
            log.error("LibraryRepository/selectBooksByAuthor error", e);
            return null;
        }
    }

    public Long countBooks() {
        try {
            log.info("LibraryRepository/countBooks");
            return dsl.selectCount()
                    .from(BOOKS)
                    .fetchOneInto(Long.class);
        } catch (Exception e) {
            log.error("LibraryRepository/countBooks error", e);
            return null;
        }
    }


/* TODO
    Remove these commented methods 2 archive
 */

/*
    public List<Author> findAllAuthors() {
        log.info("...... LibraryRepository.findAllAuthors");
        Result<Record> result = dsl.select().from(AUTHORS).fetch();
        List<Author> authors = new ArrayList<>();

        if (result.isNotEmpty()) {
            result.forEach(r -> authors.add(new Author(r.getValue(AUTHORS.ID), r.getValue(AUTHORS.FIRST_NAME), r.getValue(AUTHORS.LAST_NAME))));
        }

        return authors;
    }
*/

/*
    public Author save(Author author) {
        return dsl.insertInto(AUTHORS)
                .values(author.getFirstName(), author.getLastName())
                .returning()
                .fetchOne()
                .into(Author.class);
    }
*/


}
