package io.github.tfrazzao.domain.model;

import io.github.tfrazzao.rest.post.modelos.CriarPostRequestBody;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "POSTAGENS")
@Getter
@NoArgsConstructor
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Postagem(CriarPostRequestBody requestBody, Usuario usuario) {
        this.usuario = usuario;
        this.texto = requestBody.getTexto();
    }
    @PrePersist
    public void prePersist() {
        this.data = LocalDateTime.now();
    }

}
