package br.fiap.com.watchlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.com.watchlist.models.Series;

public interface SerieRepository extends JpaRepository<Series, Long>{
    
}
