package com.fan.bookmanagementapi.repository;

import com.fan.bookmanagementapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByIsbn(String isbn);
}
