package br.fiap.com.watchlist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.com.watchlist.models.Filmes;


@RestController
public class FilmesController {
    

    @GetMapping("/api/filme")
    public Filmes show(){

        var filme = new Filmes("The Godfather", 2, 1, "Francis Ford Coppola", "173 min");

        return filme;

    }

}
