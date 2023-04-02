package br.fiap.com.watchlist.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.fiap.com.watchlist.models.*;;

@RestControllerAdvice
public class RestExcptionHandler {
    Logger log = LoggerFactory.getLogger(RestExcptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestValidationError>> methodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException e) {
        log.error("erro de argumento inválido");
        List<RestValidationError> errors = new ArrayList<>();
        e.getFieldErrors().forEach(v -> errors.add(new RestValidationError(400, v.getDefaultMessage(), v.getField())));
        return ResponseEntity.badRequest().body(errors);
    }
}
