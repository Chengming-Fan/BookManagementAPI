package com.fan.bookmanagementapi.service;


import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.entity.Book;
import com.fan.bookmanagementapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void should_return_saved_book_when_request_is_valid() {
        CreateBookRequest mockRequest = CreateBookRequest.builder()
            .title("Book 1")
            .author("Author 1")
            .year(2022)
            .isbn("isbn1")
            .build();

        bookService.createBook(mockRequest);

        ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepository, times(1)).save(argumentCaptor.capture());
        assertEquals(mockRequest.title(), argumentCaptor.getValue().getTitle());
        assertEquals(mockRequest.author(), argumentCaptor.getValue().getAuthor());
        assertEquals(mockRequest.year(), argumentCaptor.getValue().getYear());
        assertEquals(mockRequest.isbn(), argumentCaptor.getValue().getIsbn());
    }
}