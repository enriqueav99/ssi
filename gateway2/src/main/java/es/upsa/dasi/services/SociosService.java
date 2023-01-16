package es.upsa.dasi.services;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;

import java.util.List;
import java.util.Optional;

public interface SociosService {
    List<Socio> requestSocios() throws TiendaException;
    Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException;
    Socio addSocio(UnidentifiedSocio uSocio) throws TiendaException;
    Optional<Socio> putSocio(Socio socio) throws TiendaException;
    void deleteSocio(String codigo) throws TiendaException;
}
