package com.example.news.web.controller;

import com.example.news.exception.IllegalOperationException;
import com.example.news.exception.UnautorizedAccessException;
import com.example.news.web.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        ex.getLocalizedMessage()));
    }

    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<ErrorResponse> illegalOperation(IllegalOperationException ex){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponse(
                        ex.getLocalizedMessage()));
    }



    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> noPagingArguments(Exception ex){
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponse(
                        "В запросе необходимо указать параметры pageSize (> 0) и pageNumber (>= 0). " +
                                "Значения rankId и userId должны быть целыми числами больше нуля. " +
                                "Получить список комментариев можно только для конкретной новости, указав newsId"));
    }

    @ExceptionHandler(UnautorizedAccessException.class)
    public ResponseEntity<ErrorResponse> unauthorizedAccess(UnautorizedAccessException ex){

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponse(ex.getMessage()));
    }


}
