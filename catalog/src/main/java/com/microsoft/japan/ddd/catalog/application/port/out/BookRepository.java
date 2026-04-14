package com.microsoft.japan.ddd.catalog.application.port.out;

import java.util.List;
import java.util.Optional;

import com.microsoft.japan.ddd.catalog.domain.model.Book;
import com.microsoft.japan.ddd.catalog.domain.model.isbn.ISBN13;

public interface BookRepository {

    Optional<Book> findByIsbn13(ISBN13 isbn13);

    void save(Book book, float[] embedding);

    List<Book> findSimilar(float[] queryText);
    
}
