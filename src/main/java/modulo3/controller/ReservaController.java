package modulo3.controller;

import javax.ws.rs.Path;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import modulo3.excepciones.ReservaException;
import modulo3.modelo.dto.ReservaDTO;
import modulo3.negocio.*;

@Path("/reservas")
public class ReservaController {
	
	private final IReservaService reservaService;
	
	public ReservaController(IReservaService reservaService) {
	    this.reservaService = reservaService;
	}
	

	@GET
	@Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Listar todas las reservas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de reservas obtenida",
            content = @Content(array = @io.swagger.v3.oas.annotations.media.ArraySchema(schema = @Schema(implementation = ReservaDTO.class)))),
        @ApiResponse(responseCode = "500", description = "Error interno")
    })
    public List<ReservaDTO> listarReservas() throws ReservaException {
        return reservaService.listarReservas();
    }
}
