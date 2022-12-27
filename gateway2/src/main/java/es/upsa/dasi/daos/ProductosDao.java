package es.upsa.dasi.daos;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductosDao {
    public List<Producto> requestProductos() throws TiendaException;
    public Optional<Producto> getProductoByCodigo(String codigo)throws TiendaException;
    public Producto addProducto(UnidentifiedProducto uProducto) throws TiendaException;
    public Optional<Producto> replaceProducto(UnidentifiedProducto uProducto, String coddigo) throws TiendaException;

    public boolean deleteProducto(String codigo) throws TiendaException;
}
