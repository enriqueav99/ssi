package es.upsa.dasi.daos.impl;

import es.upsa.dasi.daos.SociosDao;
import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@ApplicationScoped
public class SociosDaoImpl implements SociosDao {
    @Override
    public List<Socio> requestSocios() throws TiendaException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8081/socios")
                .request(MediaType.APPLICATION_JSON)
                .get();

        switch (response.getStatus()){
            case 200: Socio[] socios= response.readEntity(Socio[].class);
            return List.of(socios);

            default: throw new UnknownError();
        }


    }

    @Override
    public Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8081/socios/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request(MediaType.APPLICATION_JSON)
                .get();

        switch (response.getStatus()){
            case 200: return Optional.of(response.readEntity(Socio.class));
            case 404: return Optional.empty();
            default : throw new UnknownError();
        }
    }

    @Override
    public Socio addSocio(UnidentifiedSocio uSocio) throws TiendaException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8081/socios")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(uSocio));

        switch (response.getStatus()){
            case 200: Socio newSocio = response.readEntity(Socio.class);
                        return newSocio;

            default: throw new UnknownError();
        }

    }

    @Override
    public Optional<Socio> putSocio(Socio socio) throws TiendaException {

        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8081/socios")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.json(socio));

        switch (response.getStatus()){
            case 200: Optional<Socio> optSocio = Optional.of(response.readEntity(Socio.class));
            return optSocio;

            case 404: Optional.empty();

            default: throw new UnknownError();
        }

    }

    @Override
    public boolean deleteSocio(String codigo) throws TiendaException {
        Response response = ClientBuilder.newBuilder()
                .build()
                .target("http://localhost:8081/socios/{codigo}")
                .resolveTemplate("codigo", codigo)
                .request()
                .delete();

        switch (response.getStatus()){
            case 200: return true;
            case 404: return false;
            default: throw new UnknownError();
        }
    }
}
