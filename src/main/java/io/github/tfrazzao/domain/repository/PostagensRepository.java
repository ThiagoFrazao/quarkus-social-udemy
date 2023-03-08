package io.github.tfrazzao.domain.repository;

import io.github.tfrazzao.domain.model.Postagens;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostagensRepository implements PanacheRepository<Postagens> {
}
