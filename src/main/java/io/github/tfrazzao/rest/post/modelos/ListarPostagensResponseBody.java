package io.github.tfrazzao.rest.post.modelos;

import io.github.tfrazzao.domain.model.Postagem;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ListarPostagensResponseBody {

    private final List<PostagensUsuario> postagens;

    public ListarPostagensResponseBody(List<Postagem> postagens) {
        this.postagens = postagens.stream().map(PostagensUsuario::new).collect(Collectors.toList());
    }


}
