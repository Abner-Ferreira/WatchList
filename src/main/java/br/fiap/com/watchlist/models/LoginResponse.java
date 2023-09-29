package br.fiap.com.watchlist.models;

import lombok.Data;

@Data
public class LoginResponse {
    private Token token;
    private Usuario usuario;

    public LoginResponse(Token token, Usuario usuario) {
        this.token = token;
        this.usuario = usuario;
    }

}
