package io.github.tfrazzao.rest.post.exceptions;

public class UsuarioNotFoundException extends Exception {

    public UsuarioNotFoundException(){
        super("O usuario informado nao existe.");
    }
}
