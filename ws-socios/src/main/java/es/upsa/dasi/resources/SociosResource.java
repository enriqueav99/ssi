package es.upsa.dasi.resources;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.SocioService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/socios")
@Produces(MediaType.APPLICATION_JSON)
public class SociosResource {

    @Inject
    SocioService service;

    @GET
    public Response requestSocios() throws TiendaException {

        return Response.ok()
                .entity(service.requestSocios()).build();
    }

    @GET
    @Path("/{codigo}")
    public Response requestSocio(@PathParam("codigo") String codigo) throws TiendaException {

        return service.requestSocioByCodigo(codigo).map(s-> Response.ok().entity(s).build())
                .orElseGet(() -> Response.status( Response.Status.NOT_FOUND )
                        .build());

    }

    @GET
    public Response selecSocios(@QueryParam("codigos")List<String> codigos) throws TiendaException {
        List<Socio> socios;
        if ( codigos == null )
        {
            socios = service.requestSocios();
        }else{
            socios = service.selectSocios(codigos);
        }

        return Response.ok()
                .entity(new GenericEntity<List<Socio>>(socios){})
                .build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSocio(UnidentifiedSocio uSocio) throws TiendaException {


        return Response.ok()
                .entity(service.addSocio(Socio.builder()
                        .nombre(uSocio.getNombre())
                        .email(uSocio.getEmail())
                        .build()))
                .build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateSocio(Socio socio) throws TiendaException {
        return service.updateSocio(socio)
                .map(a -> Response.ok().entity(a).build())
                .orElseGet(()->Response.status(Response.Status.NOT_FOUND).build());
    }


    @DELETE
    @Path("/{codigo}")
    public Response requestDeleteSocio(@PathParam("codigo") String codigo) throws TiendaException {


        return (service.deleteSocio(codigo))? Response.ok().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}