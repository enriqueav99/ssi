package es.upsa.dasi.daos.impl;

import es.upsa.dasi.daos.Dao;
import es.upsa.dasi.exceptions.*;
import es.upsa.dasi.model.Socio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao {

    @Inject
    DataSource dataSource;

    @Override
    public List<Socio> requestSocios() throws TiendaException {

        final String SQL_SELECT="SELECT s.codigo, s.nombre, s.email, s.saldo "
                +" FROM socios s";

        List<Socio> socios= new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SQL_SELECT)){

            while (rs.next()){
                socios.add(Socio.builder()
                                .codigo(rs.getString(1))
                                .nombre(rs.getString(2))
                                .email(rs.getString(3))
                                .saldo(rs.getDouble(4))
                        .build());
            }
        } catch (SQLException sqlException) {
            throw new UncontrolledException(sqlException);
        }


        return socios;
    }

    @Override
    public Optional<Socio> requestSocioByCodigo(String codigo) throws UncontrolledException {
        final String SQL_SELECT="SELECT s.nombre, s.email, s.saldo "
                +" FROM socios s "+
                "WHERE s.codigo = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SELECT)){

            ps.setString(1,codigo);
            try(ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(Socio.builder()
                            .codigo(codigo)
                            .nombre(rs.getString(1))
                            .email(rs.getString(2))
                            .saldo(rs.getDouble(3)).build());

                } else {
                    return Optional.empty();
                }

            }
        } catch (SQLException sqlException) {
            throw new UncontrolledException(sqlException);
        }


    }

    @Override
    public List<Socio> selectSocios(List<String> codigos) throws TiendaException {
        List<Socio> socios = new ArrayList<>();
        final String SQL_SELECT="SELECT s.nombre, s.email, s.saldo "
                +" FROM socios s "+
                "WHERE s.codigo = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SELECT)){

            for (String codigo: codigos){

                ps.clearParameters();
                ps.setString(1,codigo);
                try (ResultSet rs = ps.executeQuery()){

                    if (rs.next()){

                        socios.add(Socio.builder()
                                .codigo(codigo)
                                .nombre(rs.getString(1))
                                .email(rs.getString(2))
                                .saldo(rs.getDouble(3))
                                .build());
                    }
                }
            }

        } catch (SQLException sqlException) {
            throw new UncontrolledException(sqlException);
        }

        return socios;
    }

    @Override
    public Socio addSocio(Socio socio) throws TiendaException {

        final String SQL_INSERT = "INSERT INTO socios(codigo, nombre, email) " +
                "VALUES( nextval('seq_socios'), ?, ?)";

        String[] fields = {"codigo"};

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_INSERT, fields)) {

            ps.setString(1, socio.getNombre());
            ps.setString(2, socio.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {

                rs.next();
                return socio.toBuilder()
                        .codigo(rs.getString(1))
                        .build();

            }


        } catch (SQLException sqlException) {
           throw manageException(sqlException, socio);
        }

    }

    @Override
    public Optional<Socio> updateSocio(Socio socio) throws TiendaException {

        final String SQL_UPDATE="UPDATE socios "+
                                "SET nombre = ?, email = ?, saldo= ? "+
                                "WHERE codigo = ?";
        try(Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)){

            ps.setString(1, socio.getNombre());
            ps.setString(2,socio.getEmail());
            ps.setDouble(3,socio.getSaldo());
            ps.setString(4, socio.getCodigo());

            int count = ps.executeUpdate();
            return (count == 0)? Optional.empty() : Optional.of(socio);

        } catch (SQLException sqlException) {
            throw manageException(sqlException,socio);
        }

    }

    @Override
    public boolean deleteSocio(String codigo) throws TiendaException {
        final String SQL_DELETE="DELETE FROM socios " +
                                "WHERE codigo = ?";

        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_DELETE)){
            ps.setString(1, codigo);
            int count = ps.executeUpdate();
            if(count==0){
                return false;
            }else {return true;}


        } catch (SQLException sqlException) {
            throw new UncontrolledException(sqlException);
        }


    }


    public TiendaException manageException(SQLException sqlException, Socio socio){

        String message = sqlException.getMessage();

        if(message.contains("UK_SOCIOS.EMAIL")) return new NotNullFieldException("Socio", "Email");
        else if (message.contains("NN_SOCIOS.CODIGO")) return new NotNullFieldException("Socio", "codigo");
        else if (message.contains("NN_SOCIOS.EMAIL")) return new NotNullFieldException("Socio", "email");
        else if (message.contains("NN_SOCIOS.NOMBRE")) return new NotNullFieldException("Socio", "nombre");
        else if(message.contains("CH_SOCIOS.SALDO")) return new IncorrectFieldEntityException("Socio", "saldo", socio.getSaldo());


        return new UncontrolledException(sqlException);
    }
}
