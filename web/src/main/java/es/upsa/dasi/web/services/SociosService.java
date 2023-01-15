package es.upsa.dasi.web.services;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;

import java.util.List;
import java.util.Optional;

public interface SociosService {
    public List<Socio> requestAllSocios() throws TiendaException;
    public Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException;
    public Socio requestAddSocio(UnidentifiedSocio uSocio) throws TiendaException;
    Optional<Socio> requestUpdateSocio(Socio Socio) throws TiendaException;
    String requestDeleteSocio(String codigo) throws TiendaException;
}
