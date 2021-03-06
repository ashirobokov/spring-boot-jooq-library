/*
 * This file is generated by jOOQ.
 */
package ru.ashirobokov.library.jooq;


import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;

import ru.ashirobokov.library.jooq.tables.Authors;
import ru.ashirobokov.library.jooq.tables.AuthorsBooks;
import ru.ashirobokov.library.jooq.tables.Books;


/**
 * A class modelling indexes of tables of the <code>book_library</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index AUTHORS_PRIMARY = Indexes0.AUTHORS_PRIMARY;
    public static final Index AUTHORS_BOOKS_FK_BOOK_IN_AUTHOR_BOOK = Indexes0.AUTHORS_BOOKS_FK_BOOK_IN_AUTHOR_BOOK;
    public static final Index AUTHORS_BOOKS_PRIMARY = Indexes0.AUTHORS_BOOKS_PRIMARY;
    public static final Index BOOKS_PRIMARY = Indexes0.BOOKS_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index AUTHORS_PRIMARY = Internal.createIndex("PRIMARY", Authors.AUTHORS, new OrderField[] { Authors.AUTHORS.ID }, true);
        public static Index AUTHORS_BOOKS_FK_BOOK_IN_AUTHOR_BOOK = Internal.createIndex("fk_book_in_author_book", AuthorsBooks.AUTHORS_BOOKS, new OrderField[] { AuthorsBooks.AUTHORS_BOOKS.BOOK_ID }, false);
        public static Index AUTHORS_BOOKS_PRIMARY = Internal.createIndex("PRIMARY", AuthorsBooks.AUTHORS_BOOKS, new OrderField[] { AuthorsBooks.AUTHORS_BOOKS.AUTHOR_ID, AuthorsBooks.AUTHORS_BOOKS.BOOK_ID }, true);
        public static Index BOOKS_PRIMARY = Internal.createIndex("PRIMARY", Books.BOOKS, new OrderField[] { Books.BOOKS.ID }, true);
    }
}
