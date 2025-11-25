package br.com.gamestore.loja_api.dto;

import java.math.BigDecimal;
import java.util.List;

public record CarrinhoViewDTO(
        List<ItemCarrinhoViewDTO> itens,
        BigDecimal total
) {}
