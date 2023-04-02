package br.fiap.com.watchlist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Filmes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome do filme é obrigatório")
    private String nome;
    private int categoria_id;
    private int usuario_id;
    @NotNull(message = "O nome do diretor é obrigatório")
    private String nome_diretor;
    @NotNull(message = "A duração do filme é obrigatória")
    private String duracao;

    public Filmes(String nome, int categoria_id, int usuario_id, String nome_diretor, String duracao) {
        this.nome = nome;
        this.categoria_id = categoria_id;
        this.usuario_id = usuario_id;
        this.nome_diretor = nome_diretor;
        this.duracao = duracao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNome_diretor() {
        return nome_diretor;
    }

    public void setNome_diretor(String nome_diretor) {
        this.nome_diretor = nome_diretor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Filmes [nome=" + nome + ", categoria_id=" + categoria_id + ", usuario_id=" + usuario_id
                + ", nome_diretor=" + nome_diretor + ", duracao=" + duracao + "]";
    }

}
