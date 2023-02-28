package io.github.tfrazzao.rest.user;

import io.github.tfrazzao.domain.model.Usuario;
import io.github.tfrazzao.domain.repository.UsuarioRepository;
import io.github.tfrazzao.rest.user.modelos.CriarUsuarioRequestBody;
import io.github.tfrazzao.rest.user.modelos.OrdenacaoUsuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UsuarioRepository repositorio;

    @Inject
    public UserService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Transactional
    public boolean criarUsuario(CriarUsuarioRequestBody requestBody) {
        try {
            this.repositorio.persist(new Usuario(requestBody.getIdade(), requestBody.getNome()));
            return true;
        } catch (Exception e) {
            LOG.error("Falha ao tentar criar o Usuario {} {}", requestBody.getNome(), requestBody.getIdade(), e);
            return false;
        }
    }

    public List<Usuario> recuperarTodosUsuarios(OrdenacaoUsuario ordenacao) {
        try {
            final PanacheQuery<Usuario> usuarios;
            if(ordenacao != null) {
                usuarios = this.repositorio.findAll(Sort.by(ordenacao.getNomeColuna()));
            } else {
                usuarios = this.repositorio.findAll();
            }
            return usuarios.list();
        } catch (Exception e) {
            LOG.error("Falha ao recuperar os usuarios.", e);
            return new ArrayList<>();
        }
    }

    @Transactional
    public boolean atualizarUsuario(Long idUsuario, CriarUsuarioRequestBody requestBody) {
        try {
            Usuario usuario = this.repositorio.findById(idUsuario);
            if(usuario != null) {
                usuario.setNome(requestBody.getNome());
                usuario.setIdade(requestBody.getIdade());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOG.error("Falha ao atualizar o usuario {}", idUsuario, e);
            return false;
        }
    }

    @Transactional
    public boolean removerUsuario(Long idUsuario) {
        try {
            return this.repositorio.deleteById(idUsuario);
        } catch (Exception e) {
            return false;
        }
    }
}
