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
        Optional<Book> bookOptional = bookRepository.findBookByIsbnAndDeletedIsFalse(createBookRequest.isbn());
        if (bookOptional.isPresent()) {
            throw new BusinessException("The ISBN has already been registered");
        }
        Book book = bookMapper.mapCreateBookRequestToBook(createBookRequest);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findByIdAndDeletedIsFalse(id);
        if (bookOptional.isEmpty()) {
            throw new NotFoundException("Book not found for id is " + id);
        } else {
            return bookOptional.get();
        }
    }

    @Override
    public void updateBook(Long id, UpdateBookRequest updateBookRequest) {
        Optional<Book> bookOptional = bookRepository.findByIdAndDeletedIsFalse(id);
        if (bookOptional.isEmpty()) {
            throw new NotFoundException("Book not found for id is " + id);
        } else {
            Book updatedBook = bookMapper.mapUpdateBookRequestToBook(bookOptional.get(), updateBookRequest);
            bookRepository.save(updatedBook);
        }
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> bookOptional = bookRepository.findByIdAndDeletedIsFalse(id);
        if (bookOptional.isEmpty()) {
            throw new NotFoundException("Book not found for id is " + id);
        } else {
            Book book = bookOptional.get();
            book.setDeleted(true);
            bookRepository.save(book);
        }
    }
}
