package br.com.gamestore.loja_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

public record RegistroDTO(

        @NotBlank(message = "O nome completo não pode estar vazio!")
        String nomeCompleto,

        @NotBlank(message = "O email não pode estar vazio!")
        @Email(message = "Formato de email invalido!")
        String login,

        @NotBlank(message = "A senha não pode estar em branco!")
        @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres!")
        String senha,

        @Past(message = "A data de nascimento deve ser uma data passada!")
        LocalDate dataNascimento
) {}