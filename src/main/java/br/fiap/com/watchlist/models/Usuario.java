package br.fiap.com.watchlist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O nome do usuário é obrigatório")
    private String nome;
    private String foto_perfil;
    @NotNull(message = "O email do usuário é obrigatório")
    private String email;
    @NotBlank
    @Size(min = 11, max = 11, message = "O telefone deve conter 11 dígitos")
    private String telefone;

}
