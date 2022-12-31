package es.upsa.dasi.services.impl;

import es.upsa.dasi.daos.SociosDao;
import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.SociosService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SociosServicecImpl implements SociosService
{

    @Inject
    @RestClient
    SociosDao dao;
    @Override
    public List<Socio> requestSocios() throws TiendaException {
        return dao.requestSocios();
    }

    @Override
    public Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException {
        return dao.requestSocioByCodigo(codigo);
    }

    @Override
    public Socio addSocio(UnidentifiedSocio uSocio) throws TiendaException {
        return dao.addSocio(uSocio);
    }

    @Override
    public Optional<Socio> putSocio(Socio socio) throws TiendaException {
        return dao.putSocio(socio);
    }

    @Override
    public boolean deleteSocio(String codigo) throws TiendaException {
        return dao.deleteSocio(codigo);
    }


}
