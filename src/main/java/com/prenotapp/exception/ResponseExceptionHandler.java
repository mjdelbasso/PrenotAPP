package com.prenotapp.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionResponse> handleAllExceptions(
    Exception ex,
    WebRequest request
  ) {
    ExceptionResponse er = new ExceptionResponse(
      LocalDateTime.now(),
      ex.getMessage(),
      request.getDescription(false)
    );
    return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ModelNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleModelNotFoundException(
    ModelNotFoundException ex,
    WebRequest request
  ) {
    ExceptionResponse er = new ExceptionResponse(
      LocalDateTime.now(),
      ex.getMessage(),
      request.getDescription(false)
    );
    return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
  }
}
