package es.upsa.dasi.daos.impl;

import es.upsa.dasi.daos.ProductosDao;
import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.exceptions.UncontrolledException;
import es.upsa.dasi.model.Producto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductosDaoImpl implements ProductosDao {

    @Override
    public List<Producto> requestProductos()throws TiendaException {
        Response respuesta = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/productos")
                .request(MediaType.APPLICATION_JSON)
                .get();

        switch (respuesta.getStatus()){
            case 200: Producto[] productos = respuesta.readEntity(Producto[].class);
            return List.of(productos);

            default: throw new UnknownError();
        }
    }

    @Override
    public Optional<Producto> getProductoByCodigo(String codigo) throws TiendaException {

        Response respuesta = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/productos/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        switch (respuesta.getStatus()){
            case 200: return Optional.of(respuesta.readEntity(Producto.class));
            case 404 : return Optional.empty();
            default: throw new UnknownError();
        }

    }

    @Override
    public Producto addProducto(UnidentifiedProducto uProducto) throws TiendaException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/productos")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(uProducto));

        switch (response.getStatus()){

            case 201: return response.readEntity(Producto.class);
            default: throw new UnknownError();
        }

    }

    @Override
    public Optional<Producto> replaceProducto(UnidentifiedProducto uProducto, String coddigo) throws TiendaException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/productos/{codigo}")
                .resolveTemplate("codigo", coddigo)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(uProducto));

        switch (response.getStatus()){
            case 200: return Optional.of(response.readEntity(Producto.class));
            case 404: return Optional.empty();
            default: throw new UnknownError();
        }
    }

    @Override
    public boolean deleteProducto(String codigo) throws TiendaException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8082/productos/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request().delete();

     switch (response.getStatus()){
         case 200: return true;
         case 404: return false;
         default: throw new UnknownError();
     }
    }
}
