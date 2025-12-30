package com.buildrun.encurtadorlinkfbr.adapter.in.web.exception;

import com.buildrun.encurtadorlinkfbr.core.exception.ToDomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ToDomainException.class)
    public ProblemDetail handleDomainException(ToDomainException ex) {
        return ex.toProblemDetail();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();

        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        var problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        problem.setTitle("Your request is invalid.");
        problem.setProperty("invalid-params", errors);

        return problem;
    }

}
