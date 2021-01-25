/*
 * This file is generated by jOOQ.
 */
package ru.ashirobokov.library.jooq;


import javax.annotation.Generated;

import ru.ashirobokov.library.jooq.tables.Authors;
import ru.ashirobokov.library.jooq.tables.AuthorsBooks;
import ru.ashirobokov.library.jooq.tables.Books;


/**
 * Convenience access to all tables in book_library
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>book_library.authors</code>.
     */
    public static final Authors AUTHORS = Authors.AUTHORS;

    /**
     * The table <code>book_library.authors_books</code>.
     */
    public static final AuthorsBooks AUTHORS_BOOKS = AuthorsBooks.AUTHORS_BOOKS;

    /**
     * The table <code>book_library.books</code>.
     */
    public static final Books BOOKS = Books.BOOKS;
}
