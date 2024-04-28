package com.fan.bookmanagementapi.utils;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.entity.Book;

public class DefaultTestUtil {
    public static CreateBookRequest getDefaultCreateBookRequest() {
        return CreateBookRequest.builder()
            .title("Book 1")
            .author("Author 1")
            .year(2022)
            .isbn("isbn1")
            .build();
    }

    public static Book getDefaultBook() {
        return Book.builder().id(1L)
            .title("Book 1")
            .author("Author 1")
            .year(2022)
            .isbn("isbn1")
            .build();
    }
}
