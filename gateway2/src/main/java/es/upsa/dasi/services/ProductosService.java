package es.upsa.dasi.services;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductosService {
    public List<Producto> requestProductos() throws TiendaException;
    public Optional<Producto> getProductoByCodigo(String codigo) throws TiendaException;
    public Producto addProducto(UnidentifiedProducto uProdcuto)throws TiendaException;
    public Optional<Producto> replaceProducto(UnidentifiedProducto uProducto, String coddigo) throws TiendaException;
    public void deleteProducto(String codigo) throws TiendaException;
}
