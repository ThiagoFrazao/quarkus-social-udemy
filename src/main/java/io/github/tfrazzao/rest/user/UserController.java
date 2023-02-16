package io.github.tfrazzao.rest.user;

import io.github.tfrazzao.rest.user.modelos.CriarUsuarioRequestBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @POST
    public Response criarUsuario(CriarUsuarioRequestBody requestBody) {
        return Response.status(Response.Status.CREATED).entity(requestBody).build();
    }

}
