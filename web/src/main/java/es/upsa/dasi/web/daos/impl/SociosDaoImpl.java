package es.upsa.dasi.web.daos.impl;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.web.daos.SociosDao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SociosDaoImpl implements SociosDao {
    @Override
    public List<Socio> requestAllSocios() throws TiendaException {
        Response response = ClientBuilder.newBuilder().build()
                .target("http://localhost:8888/socios")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        switch (response.getStatusInfo().toEnum()){

            case OK: Socio[] socios = response.readEntity(Socio[].class);
                return List.of(socios);
            default:throw new UnknownError();
        }
    }

    @Override
    public Optional<Socio> requesSocioByCodigo(String codigo) throws TiendaException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8888/socios/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();


        switch (response.getStatusInfo().toEnum()){
            case OK: return Optional.of(response.readEntity(Socio.class));
            case NOT_FOUND:  return Optional.empty();
            default: throw new UnknownError();
        }

    }

    @Override
    public Socio requestAddSocio(UnidentifiedSocio uSocio) throws TiendaException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8888/socios")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(uSocio));

        switch (response.getStatusInfo().toEnum()){
            case CREATED: return response.readEntity(Socio.class);
            default: throw new UnknownError();
        }
    }

    @Override
    public Optional<Socio> requestUpdateSocio(Socio socio) throws TiendaException {
        Response response =ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8888/socios")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(socio));


        switch (response.getStatusInfo().toEnum()){
            case CREATED: return Optional.of(response.readEntity(Socio.class));
            case NOT_FOUND: return Optional.empty();
            default: throw new UnknownError();
        }
    }

    @Override
    public boolean requestDeleteSocio(String codigo) throws TiendaException {
        Response response =ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8888/socios/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();
        switch (response.getStatusInfo().toEnum()){
            case OK: return true ;
            case NOT_FOUND: return false;
            default: throw new UnknownError();
        }


    }
}
