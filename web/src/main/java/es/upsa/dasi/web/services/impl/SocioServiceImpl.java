package es.upsa.dasi.web.services.impl;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.web.daos.SociosDao;
import es.upsa.dasi.web.services.SociosService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SocioServiceImpl implements SociosService {
    @Inject
    SociosDao dao;

    @Override
    public List<Socio> requestAllSocios() throws TiendaException {
        return dao.requestAllSocios();
    }

    @Override
    public Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException {
        return dao.requesSocioByCodigo(codigo);
    }

    @Override
    public Socio requestAddSocio(UnidentifiedSocio uSocio) throws TiendaException {
        return dao.requestAddSocio(uSocio);
    }

    @Override
    public Optional<Socio> requestUpdateSocio(Socio socio) throws TiendaException {
        return dao.requestUpdateSocio(socio);
    }

    @Override
    public boolean requestDeleteSocio(String codigo) throws TiendaException {
        return dao.requestDeleteSocio(codigo);
    }
}
