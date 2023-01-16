package es.upsa.dasi.web.services.impl;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.web.daos.ProductosDao;
import es.upsa.dasi.web.services.ProductosService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped

public class ProductosServiceImpl implements ProductosService {


    @Inject
    ProductosDao dao;
    @Override
    public List<Producto> requestProductos() throws TiendaException {
        return dao.requestProductos();
    }

    @Override
    public Optional<Producto> requestProducto(String codigo) throws TiendaException {
        return dao.requestProducto(codigo);
    }

    @Override
    public Producto requestAddProducto(UnidentifiedProducto uProducto) throws TiendaException {
        return dao.requestAddProductos(uProducto);
    }

    @Override
    public Optional<Producto> requestUpdateProducto(UnidentifiedProducto unidentifiedProducto, String codigo) throws TiendaException {
        return dao.requestUpdateProducto(unidentifiedProducto, codigo);
    }

    @Override
    public void requestDelteProducto(String codigo) throws TiendaException {
         dao.requestDeleteProducto(codigo);
    }
}
