package br.fiap.com.watchlist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Não foi possível encontrar o recurso solicitado")
public class RestNotFoundException extends RuntimeException {
    public RestNotFoundException(String message) {
        super(message);
    }
}
