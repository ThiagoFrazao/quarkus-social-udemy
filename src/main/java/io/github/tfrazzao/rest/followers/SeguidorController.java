package io.github.tfrazzao.rest.followers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{userId}/followers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SeguidorController {

    private final SeguidorService service;

    public SeguidorController(SeguidorService service) {
        this.service = service;
    }

    @PUT
    public Response atualizarSeguidorUsuario(@PathParam("userId") Long idUsuario) {

        return Response.ok("SHow de bola").build();
    }


}
