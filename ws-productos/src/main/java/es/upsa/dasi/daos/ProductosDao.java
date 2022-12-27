package es.upsa.dasi.daos;

import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductosDao {

    public List<Producto> requestProductos() throws TiendaException;
    Optional<Producto> requestProductoByCodigo(String codigo) throws TiendaException;
    Producto addProducto(Producto producto) throws TiendaException;
    Optional<Producto> replaceProducto(Producto producto) throws TiendaException;
    void deleteProducto(String codigo) throws TiendaException;
}
