package es.upsa.dasi.web.daos;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;
import java.util.Optional;

//@RegisterProvider(TiendaResponseExceptionMapper.class)
@RegisterRestClient(configKey = "gateway-api")
@Path("/productos")
public interface ProductosDao {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> requestProductos() throws TiendaException;

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    Optional<Producto> requestProducto(@PathParam("codigo") String codigo) throws TiendaException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Producto requestAddProductos(UnidentifiedProducto uProducto)throws TiendaException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{codigo}")
    Optional<Producto> requestUpdateProducto(UnidentifiedProducto unidentifiedProducto, @PathParam("codigo") String codigo)throws TiendaException;

    @DELETE
    @Path("/{codigo}")
    void requestDeleteProducto(@PathParam("codigo") String codigo)throws TiendaException;
}
