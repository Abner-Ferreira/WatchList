package br.fiap.com.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.com.watchlist.models.Filmes;

public interface FilmeRepository extends JpaRepository<Filmes, Long>{
    
}
