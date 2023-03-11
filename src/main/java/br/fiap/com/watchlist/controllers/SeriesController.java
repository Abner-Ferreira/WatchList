package br.fiap.com.watchlist.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fiap.com.watchlist.models.Series;


@RestController
public class SeriesController {
    
    @GetMapping("/api/serie")

    public Series show(){

        var serie = new Series( "Friends" , 1, 1, "Ben Winston" , "10 temporadas"  );

        return serie;
    }

}
