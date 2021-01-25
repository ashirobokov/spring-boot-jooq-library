/*
 * This file is generated by jOOQ.
 */
package ru.ashirobokov.library.jooq;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import ru.ashirobokov.library.jooq.tables.Authors;
import ru.ashirobokov.library.jooq.tables.AuthorsBooks;
import ru.ashirobokov.library.jooq.tables.Books;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BookLibrary extends SchemaImpl {

    private static final long serialVersionUID = 1404430656;

    /**
     * The reference instance of <code>book_library</code>
     */
    public static final BookLibrary BOOK_LIBRARY = new BookLibrary();

    /**
     * The table <code>book_library.authors</code>.
     */
    public final Authors AUTHORS = ru.ashirobokov.library.jooq.tables.Authors.AUTHORS;

    /**
     * The table <code>book_library.authors_books</code>.
     */
    public final AuthorsBooks AUTHORS_BOOKS = ru.ashirobokov.library.jooq.tables.AuthorsBooks.AUTHORS_BOOKS;

    /**
     * The table <code>book_library.books</code>.
     */
    public final Books BOOKS = ru.ashirobokov.library.jooq.tables.Books.BOOKS;

    /**
     * No further instances allowed
     */
    private BookLibrary() {
        super("book_library", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Authors.AUTHORS,
            AuthorsBooks.AUTHORS_BOOKS,
            Books.BOOKS);
    }
}
