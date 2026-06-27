package com.csc3402.lab.onetomany.repositories;

import com.csc3402.lab.onetomany.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
}
