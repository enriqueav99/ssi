package es.upsa.dasi.web.daos;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;

import java.util.List;
import java.util.Optional;

public interface SociosDao {
    public List<Socio> requestAllSocios() throws TiendaException;

    Optional<Socio> requesSocioByCodigo(String codigo) throws TiendaException;

    Socio requestAddSocio(UnidentifiedSocio uSocio) throws TiendaException;

    Optional<Socio> requestUpdateSocio(Socio socio) throws TiendaException;

    boolean requestDeleteSocio(String codigo) throws TiendaException;
}
