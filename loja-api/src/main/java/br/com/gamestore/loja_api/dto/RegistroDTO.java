package br.com.gamestore.loja_api.dto;

import java.time.LocalDate;

public record RegistroDTO(
        String nomeCompleto,
        String login,
        String senha,
        LocalDate dataNascimento
) {}