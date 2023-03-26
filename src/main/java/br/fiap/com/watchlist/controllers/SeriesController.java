package br.fiap.com.watchlist.controllers;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.fiap.com.watchlist.models.Series;
import br.fiap.com.watchlist.repository.SerieRepository;


@RestController
@RequestMapping("/api/serie")
public class SeriesController {
    

    Logger log = LoggerFactory.getLogger(SeriesController.class);

    List<Series> series = new ArrayList<>();

    @Autowired
    SerieRepository repository;
    
    
    // Listar Series
    @GetMapping
    public List<Series> index(){
        // var serie = new Series( "Friends" , 1, 1, "Ben Winston" , "10 temporadas"  );
        return repository.findAll();
    }

    // Criar Serie
    @PostMapping
    public ResponseEntity<Series> create(@RequestBody Series serie) {
        log.info("Cadastrando serie: " + serie);

        repository.save(serie);

        return ResponseEntity.status(HttpStatus.CREATED).body(serie);

    }


    // Detalhes Serie
    @GetMapping("{id}")
    public ResponseEntity<Series> show(@PathVariable Long id) {
        log.info("Buscando serie com o id: " + id);

        var serieEncontrada = repository.findById(id);

        if (serieEncontrada.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serieEncontrada.get());
    }


     // Apagar Serie
     @DeleteMapping("{id}")
     public ResponseEntity<Series> destroy(@PathVariable Long id) {
         log.info("Apagando usuario com id: " + id);
         var serieEncontrada = repository.findById(id);
 
         if (serieEncontrada.isEmpty()) {
             return ResponseEntity.notFound().build();
         }
 
         series.remove(serieEncontrada.get());
         return ResponseEntity.noContent().build();
 
     }
     //Atualizar Serie
     @PutMapping("{id}")
    public ResponseEntity<Series> update(@PathVariable Long id,@RequestBody Series serie){
        log.info("alterando serie com id "+id);
        var serieEncotrada = repository.findById(id);
        if(serieEncotrada.isEmpty())
            return  ResponseEntity.notFound().build();

        serie.setId(id);
        repository.save(serie);

        return ResponseEntity.ok(serie);
     }
}
