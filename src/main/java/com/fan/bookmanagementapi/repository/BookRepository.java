package com.fan.bookmanagementapi.repository;

import com.fan.bookmanagementapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByIsbnAndDeletedIsFalse(String isbn);

    Optional<Book> findByIdAndDeletedIsFalse(Long id);

    List<Book> findAllByDeletedIsFalse();
}
