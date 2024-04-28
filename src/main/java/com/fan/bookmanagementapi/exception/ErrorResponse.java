package com.fan.bookmanagementapi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
}
