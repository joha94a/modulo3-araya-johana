package modulo3.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import modulo3.excepciones.UsuarioException;
import modulo3.modelo.dto.UsuarioDTO;
import modulo3.negocio.*;

@Path("/usuarios")
public class UsuarioController {
	
	private final IUsuarioService usuarioService;
	
	public UsuarioController(IUsuarioService usuarioService) {
	    this.usuarioService = usuarioService;
	}
	
	
	@POST
	@Path("/")
    @Operation(summary = "Crear un nuevo usuario")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
    	    @ApiResponse(responseCode = "400", description = "Error al crear el usuario")
    })
    public Response crearUsuario(@RequestBody UsuarioDTO usuario) {
    	try {
            usuarioService.nuevoUsuario(usuario); 
            return Response.ok("ok").build(); 
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("error").build();
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Listar todos los usuarios")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = UsuarioDTO.class)))),
        @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public List<UsuarioDTO> listarUsuarios() throws UsuarioException {
        return usuarioService.listarUsuarios(); 
    }
    
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public Response obtenerUsuarioPorId(@PathParam("id") int id) {
        UsuarioDTO usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
        }
    }
}
