package es.upsa.dasi.daos;

import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.exceptions.UncontrolledException;
import es.upsa.dasi.model.Socio;

import java.util.List;
import java.util.Optional;

public interface Dao {
    public List<Socio> requestSocios() throws TiendaException;
    public Optional<Socio> requestSocioByCodigo(String codigo) throws UncontrolledException;
    public List<Socio> selectSocios(List<String> codigos) throws TiendaException;
    public Socio addSocio(Socio socio) throws TiendaException;
    public Optional<Socio> updateSocio(Socio socio) throws TiendaException;
    public boolean deleteSocio(String codigo) throws TiendaException;

}
