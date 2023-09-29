    package br.fiap.com.watchlist.repository;

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.data.jpa.repository.JpaRepository;

    import br.fiap.com.watchlist.models.Filmes;
    import org.springframework.data.jpa.repository.Query;

    public interface FilmeRepository extends JpaRepository<Filmes, Long>{
        Page<Filmes> findByUsuarioIdAndDiretorContaining(Long usuarioId, String diretor,Pageable pageable);
        Page<Filmes> findByUsuarioId(Long userId,Pageable pageable);

    }
