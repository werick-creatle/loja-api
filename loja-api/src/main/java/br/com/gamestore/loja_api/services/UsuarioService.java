package br.com.gamestore.loja_api.services;

import br.com.gamestore.loja_api.dto.PerfilUpdateDTO;
import br.com.gamestore.loja_api.dto.PerfilViewDTO;
import br.com.gamestore.loja_api.model.Usuario;
import br.com.gamestore.loja_api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Esse método busca o perfil do usuario pelo login
    @Transactional(readOnly = true)
    public PerfilViewDTO getPerfil(String loginUsuario) {

        Usuario usuario = (Usuario) usuarioRepository.findByLogin(loginUsuario);

        //verificação para verificar se o usuario passado como parametro esta cadastrado
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        //Se ele passar na verificação a entidade(com senha ) é convertida  para um DTO sem senha
        return new PerfilViewDTO(usuario);
    }

    //E esse é o metodo q atualiza as informações do usuario
    @Transactional
    public PerfilViewDTO updatePerfil(String loginusuario, PerfilUpdateDTO dados) {
        Usuario usuario = (Usuario) usuarioRepository.findByLogin(loginusuario);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        //Aqui eu atualizo os campos do objeto vivo(Conectado ao banco)
        usuario.setNomeCompleto(dados.nomeCompleto());
        usuario.setDataNascimento(dados.dataNascimento());
        usuario.setLogin(dados.l);

        //@Transactional vai salvar(fazer o commit ) automaticamente
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new PerfilViewDTO(usuarioAtualizado);
    }
}
