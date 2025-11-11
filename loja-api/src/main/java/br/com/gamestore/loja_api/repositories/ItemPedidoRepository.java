package br.com.gamestore.loja_api.repositories;

import br.com.gamestore.loja_api.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}