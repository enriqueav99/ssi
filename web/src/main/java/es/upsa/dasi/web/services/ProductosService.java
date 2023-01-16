package es.upsa.dasi.web.services;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductosService {
    public List<Producto> requestProductos() throws TiendaException;
    public Optional<Producto> requestProducto(String codigo)throws TiendaException;
    public Producto requestAddProducto(UnidentifiedProducto uProducto)throws TiendaException;
    public Optional<Producto> requestUpdateProducto(UnidentifiedProducto unidentifiedProducto, String codigo)throws TiendaException;
    public void requestDelteProducto(String codigo)throws TiendaException;
}
