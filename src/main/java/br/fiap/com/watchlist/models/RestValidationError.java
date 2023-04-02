package br.fiap.com.watchlist.models;

public record RestValidationError(Integer status, String message, String field) {

}
