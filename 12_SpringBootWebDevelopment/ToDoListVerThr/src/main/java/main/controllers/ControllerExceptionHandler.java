package main.controllers;

import main.exception.EntityNotFoundException;
import main.exception.MyRequestException;
import main.model.TodoTask;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> requestMyException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> parseDateTimeException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid query format Deadline, you must enter - 2020-12-01T22:42");
    }

    @ExceptionHandler(MyRequestException.class)
    public ResponseEntity<TodoTask> putRequestException(MyRequestException e){
        return ResponseEntity.badRequest().body(e.getTask());
    }


}
