package br.com.jcr.hrbitcoinpredict.resources;

import br.com.jcr.hrbitcoinpredict.entities.ResultData;
import br.com.jcr.hrbitcoinpredict.utils.models.LogPattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Slf4j
@RestControllerAdvice
public class BitcoinResourceExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResultData> handleConnversion(RuntimeException ex) {
        String message = "Data invalida!" + ex.getMessage();
        log.error(String.format(LogPattern.builder()
                .classLogging("BitcoinResourceExceptionHandler")
                .methodLogging("handleConnversion")
                .dateTime(LocalDateTime.now())
                .message(message)
                .status(HttpStatus.BAD_REQUEST.value())
                .build().toString()
        ));
        return ResponseEntity
                .badRequest()
                .body(
                        ResultData.builder()
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message(message)
                                .build()
                );


    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResultData> handleInternalError(RuntimeException ex) {
        String message = "Data invalida!" + ex.getMessage();
        log.error(String.format(LogPattern.builder()
                .classLogging("BitcoinResourceExceptionHandler")
                .methodLogging("handleInternalError")
                .dateTime(LocalDateTime.now())
                .message(message)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build().toString()
        ));
        return ResponseEntity
                .internalServerError()
                .body(
                        ResultData.builder()
                                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(message)
                                .build()
                );
    }
}