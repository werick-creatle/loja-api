package br.com.gamestore.loja_api.controllers;

import br.com.gamestore.loja_api.dto.CarrinhoViewDTO;
import org.springframework.security.core.Authentication;
import br.com.gamestore.loja_api.dto.ItemAdicionarDTO;
import br.com.gamestore.loja_api.model.Usuario;
import br.com.gamestore.loja_api.services.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrinho")
//@CrossOrigin("*")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionarAoCarrinho(@Valid @RequestBody ItemAdicionarDTO dados, Authentication authentication) { //Esse authentication pega o cracha do usuario logado, pois ele só pode adicionar intes ao carrinho se ele estiver logado
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        carrinhoService.adicionarAoCarrinho(dados, usuarioLogado);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint para visualizar o conteúdo do carrinho do usuário logado.
     * Protegido pela regra /api/carrinho/** no SecurityConfig.
     */

    @GetMapping
    public ResponseEntity<CarrinhoViewDTO> verCarrinho(Authentication authentication) {
        //Pega o usuario logado (dono do cracha )
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        //Chama o Gerente (Sevice ) para buscar e formatar o carrinho
        CarrinhoViewDTO carrinhoDTO = carrinhoService.verCarrinho(usuarioLogado.getUsername());

        //Retorna 200 ok com o carrinho (em formato DTO) no corpo
        return ResponseEntity.ok(carrinhoDTO);

    }
}
