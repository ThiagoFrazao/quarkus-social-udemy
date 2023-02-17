package io.github.tfrazzao.rest.user;

import io.github.tfrazzao.rest.user.modelos.CriarUsuarioRequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    public Response criarUsuario(CriarUsuarioRequestBody requestBody) {
        boolean retorno = this.userService.criarUsuario(requestBody);
        String msgRetorno = retorno ? "Usuario criado com sucesso" :
                "Nao foi possivel criar o usuario";
        Response.Status status = retorno ? Response.Status.CREATED : Response.Status.INTERNAL_SERVER_ERROR;
        return Response
                .status(status)
                .entity(msgRetorno)
                .build();
    }
    @GET
    public Response listarUsuarios() {
        return Response.ok(this.userService.recuperarTodosUsuarios()).build();
    }

}