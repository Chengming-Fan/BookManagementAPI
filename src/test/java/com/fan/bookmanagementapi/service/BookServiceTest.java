package com.fan.bookmanagementapi.service;


import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.entity.Book;
import com.fan.bookmanagementapi.exception.BusinessException;
import com.fan.bookmanagementapi.exception.NotFoundException;
import com.fan.bookmanagementapi.repository.BookRepository;
import com.fan.bookmanagementapi.utils.DefaultTestUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookServiceImpl bookService;

    @Nested
    class CreateTest {
        @Test
        void should_return_saved_book_when_request_is_valid() {
            CreateBookRequest mockRequest = DefaultTestUtil.getDefaultCreateBookRequest();

            bookService.createBook(mockRequest);

            ArgumentCaptor<Book> argumentCaptor = ArgumentCaptor.forClass(Book.class);
            verify(bookRepository, times(1)).save(argumentCaptor.capture());
            assertEquals(mockRequest.title(), argumentCaptor.getValue().getTitle());
            assertEquals(mockRequest.author(), argumentCaptor.getValue().getAuthor());
            assertEquals(mockRequest.year(), argumentCaptor.getValue().getYear());
            assertEquals(mockRequest.isbn(), argumentCaptor.getValue().getIsbn());
        }

        @Test
        void should_throw_exception_when_isbn_is_registered() {
            CreateBookRequest mockRequest = DefaultTestUtil.getDefaultCreateBookRequest();
            when(bookRepository.findBookByIsbn(mockRequest.isbn())).thenReturn(DefaultTestUtil.getDefaultBook());

            BusinessException businessException = assertThrows(BusinessException.class, () -> bookService.createBook(mockRequest));
            assertEquals("The ISBN has already been registered", businessException.getMessage());
        }
    }

    @Nested
    class QueryTest {
        @Test
        void should_return_book_detail_when_id_is_existed() {
            Long mockId = 1L;
            when(bookRepository.findById(mockId)).thenReturn(Optional.ofNullable(DefaultTestUtil.getDefaultBook()));

            Book book = bookService.getBookById(mockId);
            assertEquals(mockId, book.getId());
            assertNotNull(book.getTitle());
        }

        @Test
        void should_throw_not_found_exception_when_id_is_not_existed() {
            Long mockId = 1L;
            when(bookRepository.findById(mockId)).thenReturn(Optional.empty());

            NotFoundException businessException = assertThrows(NotFoundException.class, () -> bookService.getBookById(mockId));
            assertEquals("Book not found for id is " + mockId, businessException.getMessage());
        }
    }


}
