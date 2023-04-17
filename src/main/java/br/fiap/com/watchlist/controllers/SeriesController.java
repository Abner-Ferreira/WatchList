package br.fiap.com.watchlist.controllers;



import java.util.ArrayList;
import java.util.List;

import br.fiap.com.watchlist.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.fiap.com.watchlist.models.Series;
import br.fiap.com.watchlist.repository.SerieRepository;
import org.springframework.web.server.ResponseStatusException;


@RestController
@Slf4j
@RequestMapping("/api/serie")
public class SeriesController {
    List<Series> series = new ArrayList<>();

    @Autowired
    SerieRepository serieRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    
    
    // Listar Series
    @GetMapping
    public Page<Series> index(@RequestParam(required = false) String nome_diretor, @PageableDefault(size = 5)Pageable pageable){
        if (nome_diretor==null)
            return serieRepository.findAll(pageable);
        return  serieRepository.findByDiretorContaining(nome_diretor, pageable);
    }

    // Criar Serie
    @PostMapping
    public ResponseEntity<Series> create(@RequestBody @Valid Series serie) {
        log.info("Cadastrando serie: " + serie);
        serieRepository.save(serie);
        serie.setUsuario(usuarioRepository.findById(serie.getUsuario().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(serie);

    }


    // Detalhes Serie
    @GetMapping("{id}")
    public ResponseEntity<Series> show(@PathVariable Long id) {
        log.info("Buscando serie com o id: " + id);
        return ResponseEntity.ok(getSerie(id));
    }


     // Apagar Serie
     @DeleteMapping("{id}")
     public ResponseEntity<Series> destroy(@PathVariable Long id) {
         log.info("Apagando usuario com id: " + id);
         series.remove(getSerie(id));
         return ResponseEntity.noContent().build();
 
     }
     //Atualizar Serie
     @PutMapping("{id}")
    public ResponseEntity<Series> update(@PathVariable Long id,@RequestBody @Valid Series serie){
        log.info("alterando serie com id "+id);
        getSerie(id);
        serie.setId(id);
        serieRepository.save(serie);

        return ResponseEntity.ok(serie);
     }

     private Series getSerie(Long id){
         return serieRepository.findById(id)
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Série não encontrado"));
     }
}
