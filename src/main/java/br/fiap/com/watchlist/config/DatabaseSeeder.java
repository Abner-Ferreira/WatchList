package br.fiap.com.watchlist.config;

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

    @Override
    public void run(String... args) throws Exception {
        Usuario u1 = new Usuario(1L,"Kleber","https://via.placeholder.com/500x500.png?text=Foto+de+Kleber","klebinho_da_massa@mail.com","1234senha", "11987654321");
        Usuario u2 = new Usuario(2L, "Ana", "https://via.placeholder.com/500x500.png?text=Foto+de+Ana", "ana@mail.com", "senha1234","11912345678");
        Usuario u3 = new Usuario(3L, "Lucas", "https://via.placeholder.com/500x500.png?text=Foto+de+Lucas", "lucas@mail.com", "luc@s2015","11923456789");
        Usuario u4 = new Usuario(4L, "Camila", "https://via.placeholder.com/500x500.png?text=Foto+de+Camila", "camila@mail.com", "Camilaa2007","11934567890");
        Usuario u5 = new Usuario(5L, "Pedro", "https://via.placeholder.com/500x500.png?text=Foto+de+Pedro", "pedro@mail.com", "12345Pedro", "11945678901");
        usuarioRepository.saveAll(List.of(u1,u2,u3,u4,u5));

        serieRepository.saveAll(List.of(
                Series.builder()
                        .nome("The Sopranos")
                        .categoria_id(1)
                        .usuario(u1)
                        .diretor("David Chase")
                        .duracao("6 temporadas")
                        .build(),
                Series.builder()
                        .nome("Breaking Bad")
                        .categoria_id(2)
                        .usuario(u2)
                        .diretor("Vince Gilligan")
                        .duracao("5 temporadas")
                        .build(),
                Series.builder()
                        .nome("Game of Thrones")
                        .categoria_id(3)
                        .usuario(u3)
                        .diretor("David Benioff, D. B. Weiss")
                        .duracao("8 temporadas")
                        .build(),
                Series.builder()
                        .nome("The Wire")
                        .categoria_id(4)
                        .usuario(u4)
                        .diretor("David Simon")
                        .duracao("5 temporadas")
                        .build(),
                Series.builder()
                        .nome("Mad Men")
                        .categoria_id(5)
                        .usuario(u5)
                        .diretor("Matthew Weiner")
                        .duracao("7 temporadas")
                        .build(),
                Series.builder()
                        .nome("Friends")
                        .categoria_id(3)
                        .usuario(u3)
                        .diretor("David Crane, Marta Kauffman")
                        .duracao("10 temporadas")
                        .build(),
                Series.builder()
                        .nome("The Office")
                        .categoria_id(4)
                        .usuario(u4)
                        .diretor("Greg Daniels")
                        .duracao("9 temporadas")
                        .build(),
                Series.builder()
                        .nome("Stranger Things")
                        .categoria_id(5)
                        .usuario(u5)
                        .diretor("The Duffer Brothers")
                        .duracao("4 temporadas")
                        .build(),
                Series.builder()
                        .nome("The Crown")
                        .categoria_id(3)
                        .usuario(u3)
                        .diretor("Peter Morgan")
                        .duracao("4 temporadas")
                        .build(),
                Series.builder()
                        .nome("Cidade dos Homens")
                        .categoria_id(5)
                        .usuario(u5)
                        .diretor("Paulo Morelli")
                        .duracao("4 temporadas")
                        .build()
        ));

        filmeRepository.saveAll(List.of(
             Filmes.builder()
                     .nome("The Godfather")
                     .categoria_id(1)
                     .usuario(u1)
                     .diretor("Francis Ford Coppola")
                     .duracao("175 min")
                     .build(),
             Filmes.builder()
                     .nome("The Shawshank Redemption")
                     .categoria_id(2)
                     .usuario(u2)
                     .diretor("Frank Darabont")
                     .duracao("142 min")
                     .build(),
             Filmes.builder()
                    .nome("The Dark Knight")
                    .categoria_id(3)
                    .usuario(u3)
                    .diretor("Christopher Nolan")
                    .duracao("152 min")
                    .build(),
             Filmes.builder()
                    .nome("Pulp Fiction")
                    .categoria_id(4)
                    .usuario(u4)
                    .diretor("Quentin Tarantino")
                    .duracao("154 min")
                    .build(),
             Filmes.builder()
                    .nome("The Lord of the Rings: The Fellowship of the Ring")
                    .categoria_id(5)
                    .usuario(u5)
                    .diretor("Peter Jackson")
                    .duracao("178 min")
                    .build(),
             Filmes.builder()
                    .nome("Forrest Gump")
                    .categoria_id(6)
                    .usuario(u1)
                    .diretor("Robert Zemeckis")
                    .duracao("142 min")
                    .build(),
             Filmes.builder()
                    .nome("The Matrix")
                    .categoria_id(7)
                    .usuario(u2)
                    .diretor("The Wachowski Brothers")
                    .duracao("136 min")
                    .build(),
             Filmes.builder()
                    .nome("Goodfellas")
                    .categoria_id(8)
                    .usuario(u3)
                    .diretor("Martin Scorsese")
                    .duracao("146 min")
                    .build(),
             Filmes.builder()
                    .nome("The Silence of the Lambs")
                    .categoria_id(9)
                    .usuario(u4)
                    .diretor("Jonathan Demme")
                    .duracao("118 min")
                    .build(),
             Filmes.builder()
                    .nome("Fight Club")
                    .categoria_id(10)
                    .usuario(u5)
                    .diretor("David Fincher")
                    .duracao("139 min")
                    .build()
        ));
    }
}
