package es.upsa.dasi.cache;

import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.SocioService;
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
public class ServiceCacheDecorator implements SocioService {

    @Inject
    Logger logger;

    @Inject
    @Any
    @Delegate
    SocioService service;

    @Inject
    RedisDataSource redisDatasource;

    ValueCommands<String, Socio> sociosRedisCommands;

    @PostConstruct
    void init()
    {
        this.sociosRedisCommands = redisDatasource.value(Socio.class);
    }

    public List<Socio> requestSocios() throws TiendaException {
        return service.requestSocios();
    }

    @Override
    public Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException {


        Optional<Socio> optSocio = Optional.ofNullable( sociosRedisCommands.get(codigo) );
        if (optSocio.isEmpty() )
        {
            logger.info("No estaba en cache - " + codigo);
            optSocio = service.requestSocioByCodigo(codigo);
            optSocio.ifPresent( socio -> sociosRedisCommands.set(codigo, socio) );
        }
        else
        {
            logger.info("Encontrado en cache - " + codigo);
        }
        return optSocio;
    }

    @Override
    public List<Socio> selectSocios(List<String> codigos) throws TiendaException {
/**
 List<Socio> socios=new ArrayList<>();

 for (String codigo: codigos) {
 Optional<Socio> optSocio = Optional.ofNullable( sociosRedisCommands.get(codigo) );
 if (optSocio.isEmpty() )
 {
 //no estaba en cache
 Optional<Socio> aux = service.requestSocioByCodigo(codigo);
 aux.ifPresent( socio -> sociosRedisCommands.set(codigo, socio) );
 aux.ifPresent( socio -> socios.add(socio) );
 }
 //si estaba en cache
 optSocio.ifPresent(socio -> socios.add(socio));
 }

 return socios; **/
        return selectSocios(codigos);
    }

    @Override
    public Socio addSocio(Socio socio) throws TiendaException {
        Socio addedSocio = service.addSocio(socio);
        sociosRedisCommands.set(addedSocio.getCodigo(), addedSocio);
        return addedSocio;
    }

    @Override
    public Optional<Socio> updateSocio(Socio socio) throws TiendaException {
        Optional<Socio> optUpdatedSocio = service.updateSocio(socio);
        optUpdatedSocio.ifPresent( optsocio -> sociosRedisCommands.set(optsocio.getCodigo(), optsocio) );
        return optUpdatedSocio;
    }

    @Override
    public boolean deleteSocio(String codigo) throws TiendaException {

        Socio alumnoCachedDeleted = sociosRedisCommands.getdel(codigo);
        return service.deleteSocio(codigo);
    }
}
