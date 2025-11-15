/*
*Este DTO (PerfilUpdateDTO) será usado para receber os dados que o usuário quer alterar.
*(Não vamos permitir que ele altere o login/email ou a senha por esta rota).
*/

package br.com.gamestore.loja_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

//Eu usei as mesmas validações que eu já tinha usado no RegistroDTO
public record PerfilUpdateDTO(
        @NotBlank(message = "O nome completo não pode estar em branco!")
        String nomeCompleto,
        @Past(message = "A data de nascimento deve ser uma data no passado!")
        LocalDate dataNascimento
) {}
