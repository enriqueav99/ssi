package es.upsa.dasi.daos;

import es.upsa.dasi.daos.providers.TiendaResponseExceptionMapper;
import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
@RegisterProvider(TiendaResponseExceptionMapper.class)
@RegisterRestClient(configKey = "productos-api")
@Path("productos")
public interface ProductosDao {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> requestProductos() throws TiendaException;
    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<Producto> getProductoByCodigo(@PathParam("codigo") String codigo)throws TiendaException;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Producto addProducto(UnidentifiedProducto uProducto) throws TiendaException;

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")
    public Optional<Producto> replaceProducto(UnidentifiedProducto uProducto, @PathParam("codigo") String coddigo) throws TiendaException;

    @DELETE
    @Path("{codigo}")
    public void deleteProducto(@PathParam("codigo") String codigo) throws TiendaException;
}
