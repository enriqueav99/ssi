package es.upsa.dasi.web.daos;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.Optional;

@RegisterRestClient(configKey = "gateway-api")
@Path("/socios")
public interface SociosDao {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Socio> requestAllSocios() throws TiendaException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{codigo}")
    Optional<Socio> requesSocioByCodigo(@PathParam("codigo") String codigo) throws TiendaException;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Socio requestAddSocio(UnidentifiedSocio uSocio) throws TiendaException;

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<Socio> requestUpdateSocio(Socio socio) throws TiendaException;

    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.TEXT_PLAIN)
    String requestDeleteSocio(@PathParam("codigo") String codigo) throws TiendaException;
}
