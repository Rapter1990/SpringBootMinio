package com.springminio.app.exception.error;

import com.springminio.app.exception.ApiError;
import com.springminio.app.exception.FileResponseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handleHttpMediaTypeNotSupported : triggers when the JSON is invalid
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<String>();


        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        details.add(builder.toString());

        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now() ,
                "Invalid JSON", details);

        return ResponseEntity.status(status).body(err);

    }

    // handleHttpMessageNotReadable : triggers when the JSON is malformed
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now() ,
                "Malformed JSON request", details);


        return ResponseEntity.status(status).body(err);
    }

    // handleMethodArgumentNotValid : triggers when @Valid fails
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());


        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now() ,
                "Validation Errors", details);

        return ResponseEntity.status(status).body(err);
    }

    // handleMissingServletRequestParameter : triggers when there are missing parameters
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getParameterName() + " parameter is missing");

        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now() ,
                "Missing Parameters", details);

        return ResponseEntity.status(status).body(err);
    }

    // handleCategoryNotFoundException : triggers when there is not resource with the specified ID in Category
    @ExceptionHandler(FileResponseException.class)
    public ResponseEntity<Object> handleFileResponseNotFoundException(FileResponseException ex) {

        List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());

        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, LocalDateTime.now() ,
                "FileResponseException", details);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
