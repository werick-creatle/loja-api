package br.com.gamestore.loja_api.repositories;

import br.com.gamestore.loja_api.model.ItemDoCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemDoCarrinhoRepository extends JpaRepository<ItemDoCarrinho, Long> {

    /*
    * query metod spring Data Jpa
    *Me encontre o itemCarrinho pelo ID (opitional) buscando peli ID do carrinho e pelo ID do jogo
     */

    Optional<ItemDoCarrinho> findByCarrinhoIdAndJogoId (Long carrinhoID, Long jogoID);

}
