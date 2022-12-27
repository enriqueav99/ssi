package es.upsa.dasi.services.impl;

import es.upsa.dasi.daos.Dao;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.SocioService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ServiceImpl implements SocioService {

    @Inject
    Dao dao;
    @Override
    public List<Socio> requestSocios() throws TiendaException {
        return dao.requestSocios();
    }

    @Override
    public Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException {
        return dao.requestSocioByCodigo(codigo);
    }

    @Override
    public List<Socio> selectSocios(List<String> codigos) throws TiendaException {
        return dao.selectSocios(codigos);
    }


    @Override
    public Socio addSocio(Socio socio) throws TiendaException {
        return dao.addSocio(socio);
    }

    @Override
    public Optional<Socio> updateSocio(Socio socio) throws TiendaException {
        return dao.updateSocio(socio);
    }

    @Override
    public boolean deleteSocio(String codigo) throws TiendaException {
        return dao.deleteSocio(codigo);

    }
}
