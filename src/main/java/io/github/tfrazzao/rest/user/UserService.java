package io.github.tfrazzao.rest.user;

import io.github.tfrazzao.domain.model.Usuario;
import io.github.tfrazzao.rest.user.modelos.CriarUsuarioRequestBody;
import io.github.tfrazzao.rest.user.modelos.OrdenacaoUsuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {


    @Transactional
    public boolean criarUsuario(CriarUsuarioRequestBody requestBody) {
        try {
            Usuario novoUsuario = new Usuario(requestBody.getIdade(), requestBody.getNome());
            novoUsuario.persist();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Usuario> recuperarTodosUsuarios(OrdenacaoUsuario ordenacao) {
        try {
            final PanacheQuery<Usuario> usuarios;
            if(ordenacao != null) {
                usuarios = Usuario.findAll(Sort.by(ordenacao.getNomeColuna()));
            } else {
                usuarios = Usuario.findAll();
            }
            return usuarios.list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Transactional
    public boolean atualizarUsuario(Long idUsuario, CriarUsuarioRequestBody requestBody) {
        try {
            Usuario usuario = Usuario.findById(idUsuario);
            if(usuario != null) {
                usuario.setNome(requestBody.getNome());
                usuario.setIdade(requestBody.getIdade());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean removerUsuario(Long idUsuario) {
        try {
            return Usuario.deleteById(idUsuario);
        } catch (Exception e) {
            return false;
        }
    }
}
