package es.upsa.dasi.web.daos.impl;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.web.daos.ProductosDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductosDaoImpl implements ProductosDao {
    @Override
    public List<Producto> requestProductos() throws TiendaException {

        Response response = ClientBuilder.newBuilder().build()
                .target("http://localhost:8888/productos")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        switch (response.getStatusInfo().toEnum()){

            case OK: return List.of(response.readEntity(Producto[].class));
            default: throw new UnknownError();
        }

    }

    @Override
    public Optional<Producto> requestProducto(String codigo) throws TiendaException {

        Response response = ClientBuilder.newBuilder().build()
                .target("http://localhost:8888/productos/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        switch (response.getStatusInfo().toEnum()){

            case OK: return Optional.of(response.readEntity(Producto.class));
            case NOT_FOUND: return Optional.empty();
            default: throw new UnknownError();
        }
    }

    @Override
    public Producto requestAddProductos(UnidentifiedProducto uProducto) throws TiendaException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8888/productos")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(uProducto));

        switch (response.getStatusInfo().toEnum()){
            case CREATED: return response.readEntity(Producto.class);
            default: throw new UnknownError();
        }

    }

    @Override
    public Optional<Producto> requestUpdateProducto(UnidentifiedProducto unidentifiedProducto, String codigo) throws TiendaException {
        Response response= ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8888/productos/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(unidentifiedProducto));

        switch (response.getStatusInfo().toEnum()){
            case CREATED: return Optional.of(response.readEntity(Producto.class));
            case NOT_FOUND:return Optional.empty();
            default: throw new UnknownError();
        }
    }

    @Override
    public boolean requestDeleteProducto(String codigo) throws TiendaException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8888/productos/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request().delete();
    switch (response.getStatusInfo().toEnum()){
        case OK: return true;
        case NOT_FOUND: return  false;
        default: throw new UnknownError();
    }
    }


}
