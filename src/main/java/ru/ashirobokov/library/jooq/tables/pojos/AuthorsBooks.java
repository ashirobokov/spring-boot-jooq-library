/*
 * This file is generated by jOOQ.
 */
package ru.ashirobokov.library.jooq.tables.pojos;


import javax.annotation.Generated;
import java.io.Serializable;



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
public class AuthorsBooks implements Serializable {

    private static final long serialVersionUID = 1288431004;

    private Long authorId;
    private Long bookId;

    public AuthorsBooks() {}

    public AuthorsBooks(AuthorsBooks value) {
        this.authorId = value.authorId;
        this.bookId = value.bookId;
    }

    public AuthorsBooks(
        Long authorId,
        Long bookId
    ) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AuthorsBooks (");

        sb.append(authorId);
        sb.append(", ").append(bookId);

        sb.append(")");
        return sb.toString();
    }
}
