package es.upsa.dasi.resources;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.SociosService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/socios")
@Produces(MediaType.APPLICATION_JSON)
public class SociosResource {

    @Inject
    SociosService socioService;

    @Context
    UriInfo uriInfo;

    @Inject
    Uris uris;

    @GET

    public Response requestSocios() throws TiendaException {

        return Response.ok()
                .entity(new GenericEntity<List<Socio>>( socioService.requestSocios() ) {})
                .build();
    }


    @GET
    @Path("/{codigo}")
    public Response requestSocioByCodigo(@PathParam("codigo") String codigo) throws TiendaException {


        return socioService.requestSocioByCodigo(codigo)
                .map(a-> Response.ok().entity(a).build())
                .orElseGet(()-> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response addSocio(UnidentifiedSocio uSocio)throws TiendaException{

        Socio socio =socioService.addSocio(uSocio);

        return Response.created(uris.getUriForSocio(uriInfo, socio))
                .entity(socio)
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putSocio(Socio socio) throws TiendaException{

        return socioService.putSocio(socio)
                .map(s -> Response.created(uris.getUriForSocio(uriInfo, socio)).entity(s).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }


    @DELETE
    @Path("/{codigo}")
    public Response deleteSocio(@PathParam("codigo") String codigo) throws TiendaException {


        if (socioService.deleteSocio(codigo)){
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}