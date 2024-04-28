package com.fan.bookmanagementapi.service;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.entity.Book;

public interface BookService {
    Book createBook(CreateBookRequest createBookRequest);

    Book getBookById(Long id);
}
