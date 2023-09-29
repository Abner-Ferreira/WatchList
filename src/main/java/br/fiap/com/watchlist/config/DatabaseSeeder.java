package br.fiap.com.watchlist.config;

import br.fiap.com.watchlist.controllers.UsuarioController;
import br.fiap.com.watchlist.models.Filmes;
import br.fiap.com.watchlist.models.Series;
import br.fiap.com.watchlist.models.Usuario;
import br.fiap.com.watchlist.repository.FilmeRepository;
import br.fiap.com.watchlist.repository.SerieRepository;
import br.fiap.com.watchlist.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    FilmeRepository filmeRepository;
    @Autowired
    SerieRepository serieRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioController usuarioController;


    @Override
    public void run(String... args) throws Exception {
        var usuario = Usuario.builder()
                .nome("Emerson")
                .email("emerson@email.com")
                .senha("senha123")
                .build();

        usuarioController.registrar(usuario);
        serieRepository.saveAll(List.of(
                Series.builder()
                        .nome("The Sopranos")
                        .categoria_id(1)
                        .diretor("David Chase")
                        .duracao("6 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("Breaking Bad")
                        .categoria_id(2)
                        .diretor("Vince Gilligan")
                        .duracao("5 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("Game of Thrones")
                        .categoria_id(3)
                        .diretor("David Benioff, D. B. Weiss")
                        .duracao("8 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("The Wire")
                        .categoria_id(4)
                        .diretor("David Simon")
                        .duracao("5 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("Mad Men")
                        .categoria_id(5)
                        .diretor("Matthew Weiner")
                        .duracao("7 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("Friends")
                        .categoria_id(3)
                        .diretor("David Crane, Marta Kauffman")
                        .duracao("10 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("The Office")
                        .categoria_id(4)
                        .diretor("Greg Daniels")
                        .duracao("9 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("Stranger Things")
                        .categoria_id(5)
                        .diretor("The Duffer Brothers")
                        .duracao("4 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("The Crown")
                        .categoria_id(3)
                        .diretor("Peter Morgan")
                        .duracao("4 temporadas")
                        .usuario(usuario)
                        .build(),
                Series.builder()
                        .nome("Cidade dos Homens")
                        .categoria_id(5)
                        .diretor("Paulo Morelli")
                        .duracao("4 temporadas")
                        .usuario(usuario)
                        .build()
        ));

        filmeRepository.saveAll(List.of(
             Filmes.builder()
                     .nome("The Godfather")
                     .categoria_id(1)
                     .diretor("Francis Ford Coppola")
                     .duracao("175 min")
                     .usuario(usuario)
                     .build(),
             Filmes.builder()
                     .nome("The Shawshank Redemption")
                     .categoria_id(2)
                     .diretor("Frank Darabont")
                     .duracao("142 min")
                     .usuario(usuario)
                     .build(),
             Filmes.builder()
                    .nome("The Dark Knight")
                    .categoria_id(3)
                    .diretor("Christopher Nolan")
                    .duracao("152 min")
                     .usuario(usuario)
                    .build(),
             Filmes.builder()
                    .nome("Pulp Fiction")
                    .categoria_id(4)
                    .diretor("Quentin Tarantino")
                    .duracao("154 min")
                     .usuario(usuario)
                    .build(),
             Filmes.builder()
                    .nome("The Lord of the Rings: The Fellowship of the Ring")
                    .categoria_id(5)
                    .diretor("Peter Jackson")
                    .duracao("178 min")
                     .usuario(usuario)
                    .build(),
             Filmes.builder()
                    .nome("Forrest Gump")
                    .categoria_id(6)
                    .diretor("Robert Zemeckis")
                    .duracao("142 min")
                     .usuario(usuario)
                    .build(),
             Filmes.builder()
                    .nome("The Matrix")
                    .categoria_id(7)
                    .diretor("The Wachowski Brothers")
                    .duracao("136 min")
                     .usuario(usuario)
                    .build(),
             Filmes.builder()
                    .nome("Goodfellas")
                    .categoria_id(8)
                    .diretor("Martin Scorsese")
                    .duracao("146 min")
                     .usuario(usuario)
                     .usuario(usuario)
                    .build(),
             Filmes.builder()
                    .nome("The Silence of the Lambs")
                    .categoria_id(9)
                    .diretor("Jonathan Demme")
                    .duracao("118 min")
                     .usuario(usuario)
                    .build(),
             Filmes.builder()
                    .nome("Fight Club")
                    .categoria_id(10)
                    .diretor("David Fincher")
                    .duracao("139 min")
                     .usuario(usuario)
                    .build()
        ));
    }
}
