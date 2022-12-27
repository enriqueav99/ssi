package es.upsa.dasi.resources;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.ProductosService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.awt.*;
import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
public class ProductosResource {

    @Inject
    ProductosService service;
    @Inject
    Uris uris;

    @Context
    UriInfo uriInfo;

    @GET
    public Response getProductos() throws TiendaException {

        return Response.ok()
                .entity(new GenericEntity<List<Producto>>( service.requestProductos() ) {})
                .build();
    }

    @GET
    @Path("/{codigo}")
    public Response getProductoByCodigo(@PathParam("codigo") String codigo) throws TiendaException {

        return service.getProductoByCodigo(codigo)
                .map(a -> Response.ok().entity(a).build())
                .orElseGet(() ->Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProducto(UnidentifiedProducto uProducto) throws TiendaException {
         Producto producto = service.addProducto(uProducto);
        return Response.created(uris.gerUriForProducto(uriInfo, producto))
                .entity(producto)
                .build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replaceProducto(@PathParam("codigo") String codigo, UnidentifiedProducto uProducto)throws TiendaException{

        return service.replaceProducto(uProducto,codigo)
                .map( a -> Response.created(uris.gerUriForProducto(uriInfo, a)).entity(a).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());

    }


    @DELETE
    @Path("{codigo}")
    public Response deleteProducto(@PathParam("codigo") String codigo) throws TiendaException {

        if (service.deleteProducto(codigo)){
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
