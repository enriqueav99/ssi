package es.upsa.dasi.resources;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.services.ProductosService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Path("/productos")
@Tag(name = "productos")
public class ProductosResource {

    @Inject
    ProductosService service;

    @Inject
    ProductosUris uris;

    @Context
    UriInfo uriInfo;

    @GET
    public Response requestProductos() throws TiendaException {
        return Response
                .ok()
                .entity(new GenericEntity<List<Producto>>(service.requestProductos()){})
                .build();
    }


    @GET
    @Path("/{codigo}")
    public Response requestProductoByCodigo(@PathParam("codigo") String codigo) throws TiendaException {

        return service.requestProductoByCodigo(codigo)
                .map(c -> Response.ok().entity(c).build())
                .orElseGet(()-> Response.status(Response.Status.NOT_FOUND).build());

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProducto(UnidentifiedProducto uProducto) throws TiendaException {
        Producto producto=Producto.builder()
                .nombre(uProducto.getNombre())
                .descripcion(uProducto.getDescripcion())
                .precio(uProducto.getPrecio())
                .stock(uProducto.getStock())
                .build();


        Producto pn = service.addProducto(producto);
        return Response
                .created(uris.getProductoUri(uriInfo,pn.getCodigo()))
                .entity(pn)
                .build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{codigo}")
    public Response replaceProducto(@PathParam("codigo") String codigo , UnidentifiedProducto uProducto) throws TiendaException {

        Producto producto=Producto.builder()
                .codigo(codigo)
                .nombre(uProducto.getNombre())
                .descripcion(uProducto.getDescripcion())
                .precio(uProducto.getPrecio())
                .stock(uProducto.getStock())
                .build();
        return service.replaceProducto(producto)
                .map(a ->Response.ok().entity(a).build())
                .orElseGet(()->Response
                                .status(Response.Status.NOT_FOUND)
                                .build());
    }

    @DELETE
    @Path("/{codigo}")
    public Response deleteProducto(@PathParam("codigo")String codigo) throws TiendaException {
        service.deleteProducto(codigo);
        return Response.ok().build();
    }

}