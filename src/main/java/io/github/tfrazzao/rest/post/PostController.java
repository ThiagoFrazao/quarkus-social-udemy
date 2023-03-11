package io.github.tfrazzao.rest.post;

import io.github.tfrazzao.domain.model.Postagem;
import io.github.tfrazzao.rest.post.exceptions.UsuarioNotFoundException;
import io.github.tfrazzao.rest.post.modelos.CriarPostRequestBody;
import io.github.tfrazzao.rest.post.modelos.ListarPostagensResponseBody;
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
import java.util.List;

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
            List<Postagem> postagens = this.postService.recuperarListasPost(idUsuario);
            return Response
                    .status(Response.Status.OK)
                    .entity(new ListarPostagensResponseBody(postagens))
                    .build();
        } catch (UsuarioNotFoundException exp) {
            msg = exp.getMessage();
            status = Response.Status.BAD_REQUEST;
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
    public Response criarPost(@PathParam("userId") Long idUsuario, CriarPostRequestBody requestBody) {
        String msg;
        Response.Status status;
        try {
            this.postService.salvarPostUsuarioOrThrow(idUsuario, requestBody);
            msg = "Postagem salva com sucesso";
            status = Response.Status.CREATED;
        } catch (UsuarioNotFoundException exp) {
            log.error("O usuario {} nao foi encontrado.", idUsuario);
            msg = exp.getMessage();
            status = Response.Status.BAD_REQUEST;
        } catch (Exception exp) {
            log.error("Falha ao salvar post", exp);
            msg = "Ocorreu um erro inesperado ao salvar a postagem.";
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response
                .status(status)
                .entity(new ControllerResponse(msg))
                .build();
    }


}
