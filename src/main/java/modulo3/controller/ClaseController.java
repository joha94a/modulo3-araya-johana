package modulo3.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import modulo3.negocio.IClaseService;


@Path("/clases")
public class ClaseController {
	
	private final IClaseService claseService;
	
	public ClaseController(IClaseService claseService) {
	    this.claseService = claseService;
	}
	
	
	@DELETE
    @Path("/{id}")
    @Operation(summary = "Eliminar una clase (cambia el estado a false)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Clase eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Clase no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public Response eliminarClase(@PathParam("id") int id) {
        try {
            boolean eliminado = claseService.cambiarEstado(id);
            if (eliminado) {
                return Response.ok("Clase eliminada").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Clase no encontrada").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al eliminar la clase").build();
        }
    }
}
