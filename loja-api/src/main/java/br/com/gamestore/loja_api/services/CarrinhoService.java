package br.com.gamestore.loja_api.services;

import br.com.gamestore.loja_api.dto.ItemCarrinhoViewDTO;
import br.com.gamestore.loja_api.dto.CarrinhoViewDTO;

import org.springframework.transaction.annotation.Transactional;
import br.com.gamestore.loja_api.repositories.UsuarioRepository;
import br.com.gamestore.loja_api.dto.ItemAdicionarDTO;
import br.com.gamestore.loja_api.model.Carrinho;
import br.com.gamestore.loja_api.model.ItemDoCarrinho;
import br.com.gamestore.loja_api.model.Jogo;
import br.com.gamestore.loja_api.model.Usuario;
import br.com.gamestore.loja_api.repositories.ItemDoCarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CarrinhoService {

    @Autowired
    private ItemDoCarrinhoRepository itemDoCarrinhoRepository;

    @Autowired
    private JogoService jogoService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public void adicionarAoCarrinho(ItemAdicionarDTO dados, Usuario usuario) {
        Carrinho carrinho = usuario.getCarrinho();

        Jogo jogo = jogoService.buscarPorId(dados.jogoId());

        Optional<ItemDoCarrinho> optionalItem = itemDoCarrinhoRepository.findByCarrinhoIdAndJogoId(carrinho.getId(), jogo.getId());

        if (optionalItem.isPresent()) {
            ItemDoCarrinho itemExistente = optionalItem.get();
            itemExistente.setQuantidade(itemExistente.getQuantidade() + dados.quantidade());
            itemDoCarrinhoRepository.save(itemExistente);
        } else {
            ItemDoCarrinho novoitem = new ItemDoCarrinho(carrinho, jogo, dados.quantidade());
            itemDoCarrinhoRepository.save(novoitem);
        }
    }

    @Transactional(readOnly = true)
    public CarrinhoViewDTO verCarrinho(String loginUsuario) {
        Usuario usuario = (Usuario) usuarioRepository.findByLogin(loginUsuario);

        Carrinho carrinho = usuario.getCarrinho();

        List<ItemCarrinhoViewDTO> itensDTO = carrinho.getItens().stream()
                .map(item -> new ItemCarrinhoViewDTO(item))
                .collect(Collectors.toList());

        BigDecimal total = itensDTO.stream()
                .map(item -> item.subtotal())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarrinhoViewDTO(itensDTO, total);
    }

}
