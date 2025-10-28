package br.com.gamestore.loja_api.repositories;

import br.com.gamestore.loja_api.model.Jogo; // Importa a Entidade
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @Repository: (Opcional) Indica ao Spring que esta interface é um Repositório (um "Component" de acesso a dados).
 * É uma boa prática para clareza, embora o JpaRepository já seja detectado automaticamente.
 */
@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {

    // O Spring Data JPA vai ler o nome dos métodos que criarmos aqui
    // e gerar o SQL automaticamente.
    // Veremos isso mais tarde para as buscas customizadas (Xbox, PS4).
}