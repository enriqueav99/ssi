package es.upsa.dasi.web.daos;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductosDao {
    public List<Producto> requestProductos() throws TiendaException;

    Optional<Producto> requestProducto(String codigo) throws TiendaException;

    Producto requestAddProductos(UnidentifiedProducto uProducto)throws TiendaException;

    Optional<Producto> requestUpdateProducto(UnidentifiedProducto unidentifiedProducto, String codigo)throws TiendaException;

    boolean requestDeleteProducto(String codigo)throws TiendaException;
}
