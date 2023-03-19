package br.fiap.com.watchlist.controllers;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.fiap.com.watchlist.models.Series;


@RestController
public class SeriesController {
    

    Logger log = LoggerFactory.getLogger(SeriesController.class);

    List<Series> series = new ArrayList<>();

    // Listar Series
    @GetMapping("/api/serie")
    public List<Series> index(){
        // var serie = new Series( "Friends" , 1, 1, "Ben Winston" , "10 temporadas"  );
        return series;
    }

    // Criar Serie
    @PostMapping("/api/serie")
    public ResponseEntity<Series> create(@RequestBody Series serie) {
        log.info("Cadastrando serie: " + serie);
        serie.setId(series.size() + 1l);
        series.add(serie);

        return ResponseEntity.status(HttpStatus.CREATED).body(serie);

    }


    // Detalhes Serie
    @GetMapping("/api/serie/{id}")
    public ResponseEntity<Series> show(@PathVariable Long id) {
        log.info("Buscando serie com o id: " + id);

        var serieEncontrada = series.stream().filter(u -> u.getId().equals(id)).findFirst();

        if (serieEncontrada.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(serieEncontrada.get());
    }


     // Apagar Serie
     @DeleteMapping("/api/serie/{id}")
     public ResponseEntity<Series> destroy(@PathVariable Long id) {
         log.info("Apagando usuario com id: " + id);
         var serieEncontrada = series.stream().filter(d -> d.getId().equals(id)).findFirst();
 
         if (serieEncontrada.isEmpty()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
         }
 
         series.remove(serieEncontrada.get());
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
 
     }
     //Atualizar Serie
     @PutMapping("/api/serie/{id}")
    public ResponseEntity<Series> update(@PathVariable Long id,@RequestBody Series serie){
        log.info("alterando serie com id "+id);
        var serieEncotrada = series.stream().filter(d -> d.getId().equals(id)).findFirst();
        if(serieEncotrada.isEmpty())
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        series.remove((serieEncotrada.get()));
        serie.setId(id);
        series.add(serie);

        return ResponseEntity.ok(serie);
     }
}
