package es.upsa.dasi.daos;

import es.upsa.dasi.daos.providers.TiendaResponseExceptionMapper;
import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
@RegisterProvider(TiendaResponseExceptionMapper.class)
@RegisterRestClient(configKey = "socios-api")
@Path("socios")
public interface SociosDao {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Socio> requestSocios() throws TiendaException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")
    Optional<Socio> requestSocioByCodigo(@PathParam("codigo") String codigo) throws TiendaException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Socio addSocio(UnidentifiedSocio uSocio) throws TiendaException;

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Optional<Socio> putSocio(Socio socio) throws TiendaException;

    @DELETE
    @Path("{codigo}")
    void deleteSocio(@PathParam("codigo") String codigo) throws TiendaException;
}
