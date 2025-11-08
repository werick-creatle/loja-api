package br.com.gamestore.loja_api.controllers;


import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import br.com.gamestore.loja_api.dto.JogoCadastroDTO;
import org.springframework.http.HttpStatus;
import br.com.gamestore.loja_api.model.Jogo;
import br.com.gamestore.loja_api.repositories.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/jogos")
@CrossOrigin("*")
public class JogoController {


    @Autowired
    private JogoRepository jogoRepository;


    @GetMapping("/{id}")
    ResponseEntity<Jogo> buscarJogoPorId(@PathVariable Long id){
        Optional<Jogo> optionalJogo = jogoRepository.findById(id);

        //Verificação para saber se o jogo foi encontrado
        if (optionalJogo.isPresent()){
            Jogo jogoEncontrado = optionalJogo.get(); // se achar o jogo retorna o jogo
            return ResponseEntity.ok(jogoEncontrado);
        }else {
            return ResponseEntity.notFound().build();//Se não achar retorna o status da requisição
        }
    }


    @GetMapping
    public ResponseEntity<List<Jogo>> buscarTodosOsJogos() {
        // 1. Usa o repositório (injetado) para buscar TODOS os jogos no banco.
        List<Jogo> listaDeJogos = jogoRepository.findAll();

        // 2. Retorna uma resposta HTTP 200 (OK) com a lista de jogos no corpo (body).
        // O Spring automaticamente converte a 'listaDeJogos' (Java) para JSON.
        return ResponseEntity.ok(listaDeJogos);
    }
    @PostMapping
    public ResponseEntity<?> cadastrarNovoJogo(@RequestBody JogoCadastroDTO dados){
        if(jogoRepository.existsByNomeIgnoreCase(dados.nome())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Erro: Já existe um jogo cadastrado com esse nome.");
        }
        //Agora vou criar uma nova entidade a partir dos dados dos dados do DTO
        Jogo jogoNovo = new Jogo(
                null,
                dados.nome(),
                dados.descricao(),
                dados.preco(),
                dados.plataforma(),
                dados.genero(),
                dados.urlImagemCapa(),
                dados.dataLancamento()
        );
        jogoRepository.save(jogoNovo);//Aqui o metodo esta salvando o jogo no banco

        return ResponseEntity.status(HttpStatus.CREATED).body(jogoNovo);
    }

}