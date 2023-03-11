package io.github.tfrazzao.rest.post;

import io.github.tfrazzao.domain.model.Postagem;
import io.github.tfrazzao.domain.model.Usuario;
import io.github.tfrazzao.domain.repository.PostagemRepository;
import io.github.tfrazzao.domain.repository.UsuarioRepository;
import io.github.tfrazzao.rest.post.exceptions.UsuarioNotFoundException;
import io.github.tfrazzao.rest.post.modelos.CriarPostRequestBody;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PostService {


    private final PostagemRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    public PostService(PostagemRepository postRepository, UsuarioRepository usuarioRepository) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public List<Postagem> recuperarListasPost(Long idUsuario) throws UsuarioNotFoundException {
        Usuario usuario = this.usuarioRepository.findById(idUsuario);
        if(usuario != null) {
            return this.postRepository.find("usuario", Sort.by("data"), usuario).list();
        } else {
            throw new UsuarioNotFoundException();
        }
    }

    @Transactional
    public void salvarPostUsuarioOrThrow(Long idUsuario, CriarPostRequestBody requestBody) throws UsuarioNotFoundException {
        Usuario usuario = this.usuarioRepository.findById(idUsuario);
        if(usuario != null) {
            this.postRepository.persist(new Postagem(requestBody, usuario));
        } else {
            throw new UsuarioNotFoundException();
        }
    }
}
