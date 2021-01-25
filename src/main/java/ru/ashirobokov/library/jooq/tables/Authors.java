/*
 * This file is generated by jOOQ.
 */
package ru.ashirobokov.library.jooq.tables;


import java.util.Arrays;
import java.util.List;


import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import ru.ashirobokov.library.jooq.BookLibrary;
import ru.ashirobokov.library.jooq.Indexes;
import ru.ashirobokov.library.jooq.Keys;
import ru.ashirobokov.library.jooq.tables.records.AuthorsRecord;

import javax.annotation.Generated;


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
public class Authors extends TableImpl<AuthorsRecord> {

    private static final long serialVersionUID = -1759886492;

    /**
     * The reference instance of <code>book_library.authors</code>
     */
    public static final Authors AUTHORS = new Authors();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AuthorsRecord> getRecordType() {
        return AuthorsRecord.class;
    }

    /**
     * The column <code>book_library.authors.id</code>.
     */
    public final TableField<AuthorsRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>book_library.authors.first_name</code>.
     */
    public final TableField<AuthorsRecord, String> FIRST_NAME = createField(DSL.name("first_name"), org.jooq.impl.SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>book_library.authors.last_name</code>.
     */
    public final TableField<AuthorsRecord, String> LAST_NAME = createField(DSL.name("last_name"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * Create a <code>book_library.authors</code> table reference
     */
    public Authors() {
        this(DSL.name("authors"), null);
    }

    /**
     * Create an aliased <code>book_library.authors</code> table reference
     */
    public Authors(String alias) {
        this(DSL.name(alias), AUTHORS);
    }

    /**
     * Create an aliased <code>book_library.authors</code> table reference
     */
    public Authors(Name alias) {
        this(alias, AUTHORS);
    }

    private Authors(Name alias, Table<AuthorsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Authors(Name alias, Table<AuthorsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Authors(Table<O> child, ForeignKey<O, AuthorsRecord> key) {
        super(child, key, AUTHORS);
    }

    @Override
    public Schema getSchema() {
        return BookLibrary.BOOK_LIBRARY;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.AUTHORS_PRIMARY);
    }

    @Override
    public Identity<AuthorsRecord, Long> getIdentity() {
        return Keys.IDENTITY_AUTHORS;
    }

    @Override
    public UniqueKey<AuthorsRecord> getPrimaryKey() {
        return Keys.KEY_AUTHORS_PRIMARY;
    }

    @Override
    public List<UniqueKey<AuthorsRecord>> getKeys() {
        return Arrays.<UniqueKey<AuthorsRecord>>asList(Keys.KEY_AUTHORS_PRIMARY);
    }

    @Override
    public Authors as(String alias) {
        return new Authors(DSL.name(alias), this);
    }

    @Override
    public Authors as(Name alias) {
        return new Authors(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Authors rename(String name) {
        return new Authors(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Authors rename(Name name) {
        return new Authors(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
