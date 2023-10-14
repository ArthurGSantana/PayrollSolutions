package com.project.payrollSolutions.exceptionhandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.*;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionHandlerMessage> entityNotFound(NotFoundException e, HttpServletRequest request) {
        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Entity not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionHandlerMessage> argumentsNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        BindingResult result = e.getBindingResult();

        Set<String> fieldErrorSet = new HashSet<>();
        List<FieldError> fieldErrorList = result.getFieldErrors();

        StringJoiner stringJoiner = new StringJoiner(", ");
        for (FieldError fieldError : fieldErrorList) {
            if (fieldErrorSet.add(fieldError.getField())) {
                stringJoiner.add(fieldError.getField());
            }
        }

        ExceptionHandlerMessage err = new ExceptionHandlerMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Arguments not valid");
        err.setMessage("The field(s) cannot be null: [" + stringJoiner + "]");
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
