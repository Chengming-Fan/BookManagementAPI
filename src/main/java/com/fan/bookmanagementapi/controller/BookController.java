package com.fan.bookmanagementapi.controller;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.controller.request.UpdateBookRequest;
import com.fan.bookmanagementapi.entity.Book;
import com.fan.bookmanagementapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody CreateBookRequest request) {
        Book book = bookService.createBook(request);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(name = "id") Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(books);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id, @RequestBody UpdateBookRequest request) {
        bookService.updateBook(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
