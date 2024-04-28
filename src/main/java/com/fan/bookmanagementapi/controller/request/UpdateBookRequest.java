package com.fan.bookmanagementapi.controller.request;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record UpdateBookRequest(@NotNull String title,
                                String author,
                                int year,
                                String isbn) {
}
