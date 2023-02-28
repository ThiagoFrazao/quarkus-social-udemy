package io.github.tfrazzao.rest.user.modelos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CriarUsuarioRequestBody {

    @NotBlank(message = "Para criar um usuário é necessário informar nome. ")
    private String nome;

    @NotNull(message = "Para criar um usuário é necessário informar idade. ")
    private Long idade;

}
