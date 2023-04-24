package br.fiap.com.watchlist.models;

import br.fiap.com.watchlist.controllers.FilmesController;
import br.fiap.com.watchlist.controllers.UsuarioController;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Filmes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome do filme é obrigatório")
    private String nome;
    private int categoria_id;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull(message = "O nome do diretor é obrigatório")
    private String diretor;
    @NotNull(message = "A duração do filme é obrigatória")
    private String duracao;

    public EntityModel<Filmes> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(FilmesController.class).show(id)).withSelfRel(),
                linkTo(methodOn(FilmesController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(FilmesController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(UsuarioController.class).show(this.getUsuario().getId())).withRel("usuario")

        );
    }
}
