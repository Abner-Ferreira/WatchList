package br.fiap.com.watchlist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.fiap.com.watchlist.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Optional<Usuario> findByEmail(String username);
    
}
