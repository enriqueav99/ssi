package es.upsa.dasi.services.impl;

import es.upsa.dasi.daos.ProductosDao;
import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.services.ProductosService;

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
    public Optional<Producto> getProductoByCodigo(String codigo) throws TiendaException {
        return dao.getProductoByCodigo(codigo);
    }

    @Override
    public Producto addProducto(UnidentifiedProducto uProdcuto) throws TiendaException {
        return dao.addProducto(uProdcuto);
    }

    @Override
    public Optional<Producto> replaceProducto(UnidentifiedProducto uProducto, String coddigo) throws TiendaException {
        return dao.replaceProducto(uProducto, coddigo);
    }

    @Override
    public boolean deleteProducto(String codigo) throws TiendaException {
        return dao.deleteProducto(codigo);
    }
}
