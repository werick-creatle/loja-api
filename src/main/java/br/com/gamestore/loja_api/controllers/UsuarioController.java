package br.com.gamestore.loja_api.controllers;


import br.com.gamestore.loja_api.dto.PerfilUpdateDTO;
import br.com.gamestore.loja_api.dto.PerfilViewDTO;
import br.com.gamestore.loja_api.model.Usuario;
import br.com.gamestore.loja_api.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Endpoint para o usuario logado buscar o seu proprio perfil
    @GetMapping("/meuperfil")
    public ResponseEntity<PerfilViewDTO>  getMeuPerfil (Authentication authentication){
        //Pega o usuario do cracha de segurança
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        PerfilViewDTO perfil = usuarioService.getPerfil(usuarioLogado.getUsername());

        return ResponseEntity.ok(perfil);
    }
    //EndPoint para usuario logado atualizar o seu proprio perfil
    @PutMapping("/meuperfil")
    public ResponseEntity<?> updateMeuPerfil(@Valid @RequestBody PerfilUpdateDTO dados, Authentication authentication){
        try {
            Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

            PerfilViewDTO perfilAtualizado = usuarioService.updatePerfil(usuarioLogado.getUsername(), dados);

            return ResponseEntity.ok(perfilAtualizado);
        }catch (ResponseStatusException e){
            return  ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}
