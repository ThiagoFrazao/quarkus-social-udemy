package io.github.tfrazzao.rest.user;

import io.github.tfrazzao.rest.user.modelos.ControllerResponse;
import io.github.tfrazzao.rest.user.modelos.CriarUsuarioRequestBody;
import io.github.tfrazzao.rest.user.modelos.OrdenacaoUsuario;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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
import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;
    private final Validator validator;

    @Inject
    public UserController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @POST
    public Response criarUsuario(CriarUsuarioRequestBody requestBody) {
        String msgRetorno;
        Response.Status status;
        Set<ConstraintViolation<CriarUsuarioRequestBody>> violations = this.validator.validate(requestBody);
        if(violations.isEmpty()) {
            boolean retorno = this.userService.criarUsuario(requestBody);
            msgRetorno = retorno ? "Usuario criado com sucesso" : "Nao foi possivel criar o usuario";
            status = retorno ? Response.Status.CREATED : Response.Status.INTERNAL_SERVER_ERROR;
        } else {
            status = Response.Status.BAD_REQUEST;
            msgRetorno = this.recuperarMsgFromViolations(violations);
        }
        return Response
                .status(status)
                .entity(new ControllerResponse(msgRetorno))
                .build();
    }

    private String recuperarMsgFromViolations(Set<ConstraintViolation<CriarUsuarioRequestBody>> violations) {
        StringBuilder stringBuilder = new StringBuilder();
        violations.stream().map(ConstraintViolation::getMessage).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    @GET
    public Response listarUsuarios(@QueryParam("ordenacao") OrdenacaoUsuario ordenacao) {
        return Response.ok(this.userService.recuperarTodosUsuarios(ordenacao)).build();
    }

    @PUT
    @Path("/{idUsuario}")
    public Response atualizarUsuario(@PathParam("idUsuario") Long idUsuario, CriarUsuarioRequestBody requestBody) {
        String msgRetorno;
        Response.Status status;
        Set<ConstraintViolation<CriarUsuarioRequestBody>> violations = this.validator.validate(requestBody);
        if(violations.isEmpty()) {
            boolean resultado = this.userService.atualizarUsuario(idUsuario, requestBody);
            msgRetorno = resultado ? "usuario atualizado com sucesso" : "usuario nao foi encontrado";
            status = resultado ? Response.Status.OK : Response.Status.NOT_FOUND;
        } else {
            status = Response.Status.BAD_REQUEST;
            msgRetorno = this.recuperarMsgFromViolations(violations);
        }
        return Response
                .status(status)
                .entity(new ControllerResponse(msgRetorno))
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