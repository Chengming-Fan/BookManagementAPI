package com.fan.bookmanagementapi.controller;

import com.fan.bookmanagementapi.controller.request.CreateBookRequest;
import com.fan.bookmanagementapi.exception.BusinessException;
import com.fan.bookmanagementapi.exception.GlobalExceptionHandler;
import com.fan.bookmanagementapi.exception.NotFoundException;
import com.fan.bookmanagementapi.service.BookServiceImpl;
import com.fan.bookmanagementapi.utils.DefaultTestUtil;
import com.fan.bookmanagementapi.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BookController.class)
@ContextConfiguration(classes = {GlobalExceptionHandler.class, BookController.class})
public class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;

    @Test
    void should_return_201_when_create_book_given_valid_request() throws Exception {
        CreateBookRequest mockRequest = DefaultTestUtil.getDefaultCreateBookRequest();
        when(bookService.createBook(mockRequest)).thenReturn(DefaultTestUtil.getDefaultBook());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
            .post("/books")
            .content(JsonUtil.getObjectMapper().writeValueAsString(mockRequest))
            .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpectAll(
            status().isCreated()
        );
    }

    @Test
    void should_return_400_when_create_book_given_registered_isbn() throws Exception {
        CreateBookRequest mockRequest = DefaultTestUtil.getDefaultCreateBookRequest();
        String errorMsg = "The ISBN has already been registered";
        when(bookService.createBook(mockRequest)).thenThrow(new BusinessException(errorMsg));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
            .post("/books")
            .content(JsonUtil.getObjectMapper().writeValueAsString(mockRequest))
            .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpectAll(
            status().isBadRequest(),
            jsonPath("$.message").value(errorMsg)
        );
    }

    @Test
    void should_return_200_when_find_book_given_existed_id() throws Exception {
        Long mockId = 1L;
        when(bookService.getBookById(mockId)).thenReturn(DefaultTestUtil.getDefaultBook());

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
            .get("/books/" + mockId)
        );

        response.andExpectAll(
            status().isOk(),
            jsonPath("$.id").value(mockId),
            jsonPath("$.title").value("Book 1")
        );
    }

    @Test
    void should_return_404_when_find_book_given_not_existed_id() throws Exception {
        Long mockId = 1L;
        when(bookService.getBookById(mockId)).thenThrow(new NotFoundException("Book not found for id is 1"));

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
            .get("/books/" + mockId)
        );

        response.andExpectAll(
            status().isNotFound(),
            jsonPath("$.message").value("Book not found for id is 1")
        );
    }
}
