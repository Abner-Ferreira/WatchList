package br.fiap.com.watchlist.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
