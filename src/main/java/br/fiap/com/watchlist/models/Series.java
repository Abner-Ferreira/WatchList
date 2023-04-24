package br.fiap.com.watchlist.models;

import br.fiap.com.watchlist.controllers.SeriesController;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome da série é obrigatório")
    private String nome;
    private int categoria_id;
    @NotNull
    @ManyToOne
    private Usuario usuario;
    @NotNull(message = "O nome do diretor é obrigatório")
    private String diretor;
    @NotNull(message = "A duração da série é obrigatória")
    private String duracao;

    public EntityModel<Series> toEntityModel(){
        return EntityModel.of(
                this,
                linkTo(methodOn(SeriesController.class).show(id)).withSelfRel(),
                linkTo(methodOn(SeriesController.class).destroy(id)).withRel("delete"),
                linkTo(methodOn(SeriesController.class).index(null, Pageable.unpaged())).withRel("all"),
                linkTo(methodOn(UsuarioController.class).show(this.getUsuario().getId())).withRel("usuario")
        );
    }
}
