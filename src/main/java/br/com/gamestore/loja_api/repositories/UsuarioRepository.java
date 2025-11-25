package br.com.gamestore.loja_api.repositories;

import br.com.gamestore.loja_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /*
     * Query Method customizada do Spring Data JPA.
     * O Spring vai entender que quero buscar um usuário
     * pelo campo 'login'.  isso é essencial para o login dar certo
     */
    UserDetails findByLogin(String login);
}
