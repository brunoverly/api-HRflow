package br.com.HRFlow.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ErrorResponse (
        @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
        LocalDateTime timestamp,
        int status,
        String error,
        String path
){
}
