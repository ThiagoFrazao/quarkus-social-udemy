package io.github.tfrazzao.rest.user;

import io.github.tfrazzao.domain.model.Usuario;
import io.github.tfrazzao.rest.user.modelos.CriarUsuarioRequestBody;
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

    public List<Usuario> recuperarTodosUsuarios() {
        try {
            PanacheQuery<Usuario> usuarios = Usuario.findAll(Sort.by("name"));
            return usuarios.list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
