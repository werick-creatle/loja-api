package br.com.gamestore.loja_api.repositories;

import br.com.gamestore.loja_api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}