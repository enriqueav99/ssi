package es.upsa.dasi.cache;

import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.services.ProductosService;
import io.quarkus.redis.datasource.value.ValueCommands;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import io.quarkus.redis.datasource.RedisDataSource;

@Decorator
@Priority(100)
public class ServiceCacheDecorator implements ProductosService {

    @Inject
    @Any
    @Delegate
    ProductosService service;

    @Inject
    Logger logger;
    @Inject
    RedisDataSource redisDatasource;

    ValueCommands<String, Producto> productosRedisCommands;

    @PostConstruct
    void init()
    {
        logger.info("eyeyeyeyeyyeyeye--------");
        this.productosRedisCommands = redisDatasource.value(Producto.class);
    }


    @Override
    public List<Producto> requestProductos() throws TiendaException {
        return service.requestProductos();
    }

    @Override
    public Optional<Producto> requestProductoByCodigo(String codigo) throws TiendaException {
        Optional<Producto> optProducto = Optional.ofNullable( productosRedisCommands.get(codigo) );
        if (optProducto.isEmpty() )
        {
            logger.info("No estaba en cache - " + codigo);
            optProducto = service.requestProductoByCodigo(codigo);
            optProducto.ifPresent( produc -> productosRedisCommands.set(codigo, produc) );
        }
        else
        {
            logger.info("Encontrado en cache - " + codigo);
        }
        return optProducto;
    }

    @Override
    public Producto addProducto(Producto producto) throws TiendaException {
        Producto addedProducto = service.addProducto(producto);
        productosRedisCommands.set(addedProducto.getCodigo(), addedProducto);
        return addedProducto;
    }

    @Override
    public Optional<Producto> replaceProducto(Producto producto) throws TiendaException {
        Optional<Producto> optUpdatedProducto = service.replaceProducto(producto);
        optUpdatedProducto.ifPresent( produc -> productosRedisCommands.set(produc.getCodigo(), produc) );
        return optUpdatedProducto;
    }

    @Override
    public void deleteProducto(String codigo) throws TiendaException {
        Producto productoDeleted = productosRedisCommands.getdel(codigo);
        service.deleteProducto(codigo);
    }
}
