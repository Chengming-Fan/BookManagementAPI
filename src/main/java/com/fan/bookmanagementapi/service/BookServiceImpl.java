package com.fan.bookmanagementapi.service;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.entity.Book;
import com.fan.bookmanagementapi.mapper.BookMapper;
import com.fan.bookmanagementapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book createBook(CreateBookRequest createBookRequest) {
        Book book = BookMapper.INSTANCE.mapCreateBookRequestToBook(createBookRequest);
        return bookRepository.save(book);
    }
}
