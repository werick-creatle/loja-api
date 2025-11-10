package br.com.gamestore.loja_api.repositories;

import br.com.gamestore.loja_api.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{

}
