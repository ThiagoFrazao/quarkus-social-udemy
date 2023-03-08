package io.github.tfrazzao.rest.post;

import io.github.tfrazzao.rest.post.exceptions.UsuarioNotFoundException;
import io.github.tfrazzao.rest.user.modelos.ControllerResponse;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users/{userId}/posts")
@Slf4j
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GET
    public Response listarPosts(@PathParam("userId") Long idUsuario) {
        String msg;
        Response.Status status;
        try {
            boolean resultado = this.postService.recuperarListasPost(idUsuario);
            msg = "Tudo ok";
            status = Response.Status.OK;
        } catch (UsuarioNotFoundException exp) {
            msg = exp.getMessage();
            status = Response.Status.NOT_FOUND;
        } catch (Exception e) {
            log.error("Falha ao listar posts do usuario {}", idUsuario, e);
            msg = "Falha ao recupear os posts. Tente novamente mais tarde.";
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response
                .status(status)
                .entity(new ControllerResponse(msg))
                .build();
    }

    @POST
    public Response criarPost(@PathParam("userId") Long idUsuario) {
        return Response.ok().build();
    }


}
