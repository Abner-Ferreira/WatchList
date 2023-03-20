package br.fiap.com.watchlist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String foto_perfil;
    private String email;
    private String telefone;
    
    protected Usuario(){}

    public Usuario(String nome, String string, String email, String telefone) {
        this.nome = nome;
        this.foto_perfil = string;
        this.email = email;
        this.telefone = telefone;
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


    public String getFoto_perfil() {
        return foto_perfil;
    }


    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return "Usuario [nome=" + nome + ", foto_perfil=" + foto_perfil + ", email=" + email + ", telefone=" + telefone
                + "]";
    }

    
    

}
