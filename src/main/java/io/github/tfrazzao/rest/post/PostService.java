package io.github.tfrazzao.rest.post;

import io.github.tfrazzao.domain.repository.PostagensRepository;
import io.github.tfrazzao.rest.post.exceptions.UsuarioNotFoundException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostService {


    private final PostagensRepository repository;

    public PostService(PostagensRepository repository) {
        this.repository = repository;
    }


    public boolean recuperarListasPost(Long idUsuario) throws UsuarioNotFoundException {
        return false;
    }
}
