package com.fan.bookmanagementapi.service;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.controller.request.UpdateBookRequest;
import com.fan.bookmanagementapi.entity.Book;
import com.fan.bookmanagementapi.exception.BusinessException;
import com.fan.bookmanagementapi.exception.NotFoundException;
import com.fan.bookmanagementapi.mapper.BookMapper;
import com.fan.bookmanagementapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Override
    public Book createBook(CreateBookRequest createBookRequest) {
        Book bookByIsbn = bookRepository.findBookByIsbn(createBookRequest.isbn());
        if (bookByIsbn != null) {
            throw new BusinessException("The ISBN has already been registered");
        }
        Book book = bookMapper.mapCreateBookRequestToBook(createBookRequest);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("Book not found for id is " + id);
        } else {
            return book.get();
        }
    }

    @Override
    public void updateBook(Long id, UpdateBookRequest updateBookRequest) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("Book not found for id is " + id);
        } else {
            Book updatedBook = bookMapper.mapUpdateBookRequestToBook(book.get(), updateBookRequest);
            bookRepository.save(updatedBook);
        }
    }
}
