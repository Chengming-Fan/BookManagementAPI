package com.fan.bookmanagementapi.mapper;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.controller.request.UpdateBookRequest;
import com.fan.bookmanagementapi.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book mapCreateBookRequestToBook(CreateBookRequest request);

    @Mapping(target = "id", source = "existedBook.id")
    @Mapping(target = "title", source = "request.title")
    @Mapping(target = "author", source = "request.author")
    @Mapping(target = "year", source = "request.year")
    @Mapping(target = "isbn", source = "request.isbn")
    Book mapUpdateBookRequestToBook(Book existedBook, UpdateBookRequest request);
}
