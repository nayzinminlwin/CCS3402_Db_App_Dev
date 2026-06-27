package com.csc3402.lab.onetomany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.csc3402.lab.onetomany.model.Page;
import com.csc3402.lab.onetomany.model.Book;
import java.util.List;
// import domain.sort
import org.springframework.data.domain.Sort;

public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findByBook(Book book, Sort sort);
}
