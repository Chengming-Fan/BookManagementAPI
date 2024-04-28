package com.fan.bookmanagementapi.service;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.controller.request.UpdateBookRequest;
import com.fan.bookmanagementapi.entity.Book;

import java.util.List;

public interface BookService {
    Book createBook(CreateBookRequest createBookRequest);

    Book getBookById(Long id);

    void updateBook(Long id, UpdateBookRequest updateBookRequest);

    void deleteBook(Long id);

    List<Book> getBooks();
}
