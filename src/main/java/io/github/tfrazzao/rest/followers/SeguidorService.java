package io.github.tfrazzao.rest.followers;

import io.github.tfrazzao.domain.repository.SeguidorRepository;
import io.github.tfrazzao.domain.repository.UsuarioRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SeguidorService {

    private final UsuarioRepository userRepository;
    private final SeguidorRepository followRepository;

    public SeguidorService(UsuarioRepository userRepository, SeguidorRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }



}
