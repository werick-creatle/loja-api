package br.com.gamestore.loja_api.security;

import br.com.gamestore.loja_api.model.Usuario;
import br.com.gamestore.loja_api.repositories.UsuarioRepository;
import br.com.gamestore.loja_api.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var subject = tokenService.validarToken(tokenJWT);

            if (subject != null) {
                // ✅ CORREÇÃO: Recuperar a role do token JWT
                var tokenDecodificado = com.auth0.jwt.JWT.decode(tokenJWT);
                String role = tokenDecodificado.getClaim("role").asString();

                // ✅ CORREÇÃO: Buscar o usuário e fazer cast para Usuario
                var userDetails = usuarioRepository.findByLogin(subject);
                Usuario usuario = (Usuario) userDetails; // ← CAST EXPLÍCITO

                // ✅ CORREÇÃO: Usar a role do token no authentication
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        List.of(new SimpleGrantedAuthority("ROLE_" + role))
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            // Remove o "Bearer " do token
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}