/*
 * This file is generated by jOOQ.
 */
package ru.ashirobokov.library.jooq.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import ru.ashirobokov.library.jooq.tables.Authors;


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
public class AuthorsRecord extends UpdatableRecordImpl<AuthorsRecord> implements Record3<Long, String, String> {

    private static final long serialVersionUID = -728021805;

    /**
     * Setter for <code>book_library.authors.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>book_library.authors.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>book_library.authors.first_name</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>book_library.authors.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>book_library.authors.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>book_library.authors.last_name</code>.
     */
    public String getLastName() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Authors.AUTHORS.ID;
    }

    @Override
    public Field<String> field2() {
        return Authors.AUTHORS.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Authors.AUTHORS.LAST_NAME;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public AuthorsRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public AuthorsRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public AuthorsRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public AuthorsRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AuthorsRecord
     */
    public AuthorsRecord() {
        super(Authors.AUTHORS);
    }

    /**
     * Create a detached, initialised AuthorsRecord
     */
    public AuthorsRecord(Long id, String firstName, String lastName) {
        super(Authors.AUTHORS);

        set(0, id);
        set(1, firstName);
        set(2, lastName);
    }
}
