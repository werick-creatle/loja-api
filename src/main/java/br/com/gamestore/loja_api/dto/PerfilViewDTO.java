/*
* Este DTO (PerfilViewDTO) será usado para mostrar os dados do usuário com segurança,
* omitindo a senha criptografada
*/

package br.com.gamestore.loja_api.dto;

import br.com.gamestore.loja_api.model.Usuario;

import java.time.LocalDate;

public record PerfilViewDTO
        (
                Long id,
                String login,
                String nomeCompleto,
                LocalDate dataNascimento
        ) {

    //Contrutor auxiliar para converter a entidade usuario nesse DTO
    public PerfilViewDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getNomeCompleto(),
                usuario.getDataNascimento()
        );
    }
}
