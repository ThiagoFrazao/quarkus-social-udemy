package io.github.tfrazzao.rest.user;

import io.github.tfrazzao.rest.user.modelos.CriarUsuarioRequestBody;
import io.github.tfrazzao.rest.user.modelos.OrdenacaoUsuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
    public Response listarUsuarios(@QueryParam("ordenacao") OrdenacaoUsuario ordenacao) {
        return Response.ok(this.userService.recuperarTodosUsuarios(ordenacao)).build();
    }

    @PUT
    @Path("/{idUsuario}")
    public Response atualizarUsuario(@PathParam("idUsuario") Long idUsuario, CriarUsuarioRequestBody requestBody) {
        boolean resultado = this.userService.atualizarUsuario(idUsuario, requestBody);
        Response.Status status = resultado ? Response.Status.OK : Response.Status.NOT_FOUND;
        String mensagem = resultado ? "usuario atualizado com sucesso" : "usuario nao foi encontrado";
        return Response
                .status(status)
                .entity(mensagem)
                .build();
    }

    @DELETE
    @Path("/{idUsuario}")
    public Response removerUsuario(@PathParam("idUsuario") Long idUsuario) {
        boolean resultado = this.userService.removerUsuario(idUsuario);
        Response.Status status = resultado ? Response.Status.OK : Response.Status.NOT_FOUND;
        String mensagem = resultado ? "usuario removido com sucesso" : "usuario nao foi encontrado";
        return Response
                .status(status)
                .entity(mensagem)
                .build();
    }

}