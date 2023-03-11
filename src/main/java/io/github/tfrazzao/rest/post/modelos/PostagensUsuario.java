package io.github.tfrazzao.rest.post.modelos;

import io.github.tfrazzao.domain.model.Postagem;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostagensUsuario {

    private final LocalDateTime data;

    private final String texto;

    public PostagensUsuario(Postagem postagem) {
        this.data = postagem.getData();
        this.texto = postagem.getTexto();
    }

}
