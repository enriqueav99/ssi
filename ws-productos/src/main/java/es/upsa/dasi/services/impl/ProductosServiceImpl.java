package es.upsa.dasi.services.impl;

import es.upsa.dasi.daos.ProductosDao;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.ProductosService;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
    public Optional<Producto> requestProductoByCodigo(String codigo) throws TiendaException {
        return dao.requestProductoByCodigo(codigo);
    }

    @Override
    public Producto addProducto(Producto producto) throws TiendaException {
        return dao.addProducto(producto);
    }

    @Override
    public Optional<Producto> replaceProducto(Producto producto) throws TiendaException {
        return dao.replaceProducto(producto);
    }

    @Override
    public void deleteProducto(String codigo) throws TiendaException {
        dao.deleteProducto(codigo);
    }
}
