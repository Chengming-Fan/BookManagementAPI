package com.fan.bookmanagementapi.service;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.entity.Book;
import com.fan.bookmanagementapi.exception.BusinessException;
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
        Book bookByIsbn = bookRepository.findBookByIsbn(createBookRequest.isbn());
        if (bookByIsbn != null) {
            throw new BusinessException("The ISBN has already been registered");
        }
        Book book = BookMapper.INSTANCE.mapCreateBookRequestToBook(createBookRequest);
        return bookRepository.save(book);
    }
}
