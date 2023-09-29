package br.fiap.com.watchlist.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.com.watchlist.models.Series;

public interface SerieRepository extends JpaRepository<Series, Long>{
    Page <Series> findByUsuarioIdAndDiretorContaining(Long usuarioId, String diretor,Pageable pageable);
    Page <Series> findByUsuarioId(Long usuarioId,Pageable pageable);
}
