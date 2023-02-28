package io.github.tfrazzao.rest.user.modelos;

import lombok.Getter;

@Getter
public enum OrdenacaoUsuario {

    NOME("name"),
    IDADE("age");

    OrdenacaoUsuario(String nomeColuna) {
        this.nomeColuna = nomeColuna;
    }
    final String nomeColuna;
}
