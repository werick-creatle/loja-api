package br.com.gamestore.loja_api.config; // Verifique seu pacote

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration: Diz ao Spring que esta é uma classe de configuração de segurança.

@Configuration
public class SecurityConfig {

//@Bean cria um objeto (um "Bean") que o Spring criar um Bean do tipo PasswordEncoder.

    @Bean
    public PasswordEncoder passwordEncoder() {
        /*
         * BCryptPasswordEncoder é a implementação de hashing de senha
         * mais recomendada e segura que o Spring Security oferece.
         */
        return new BCryptPasswordEncoder();
    }
}