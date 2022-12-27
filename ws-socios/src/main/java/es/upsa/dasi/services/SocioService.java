package es.upsa.dasi.services;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;

import java.util.List;
import java.util.Optional;

public interface SocioService {

    public List<Socio> requestSocios()throws TiendaException;
    Optional<Socio> requestSocioByCodigo(String codigo) throws TiendaException;
    public List<Socio> selectSocios(List<String> codigos) throws TiendaException;
    public Socio addSocio(Socio socio) throws TiendaException;
    public Optional<Socio> updateSocio(Socio socio) throws TiendaException;
    public boolean deleteSocio(String codigo) throws TiendaException;


}
