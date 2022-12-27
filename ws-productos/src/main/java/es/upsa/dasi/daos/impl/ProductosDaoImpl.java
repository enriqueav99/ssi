package es.upsa.dasi.daos.impl;

import es.upsa.dasi.daos.ProductosDao;
import es.upsa.dasi.exceptions.*;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.services.ProductosService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductosDaoImpl implements ProductosDao {

    @Inject
    DataSource dataSource;


    @Override
    public List<Producto> requestProductos() throws TiendaException {
        final String SQL_SELECT="SELECT p.codigo, p.nombre, p.descripcion, p.stock, p.precio "+
                "FROM productos p";
        List<Producto> productos =new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT))
        {
            while (resultSet.next()){
                productos.add(
                        Producto.builder()
                                .codigo(resultSet.getString(1))
                                .nombre(resultSet.getString(2))
                                .descripcion(resultSet.getString(3))
                                .stock(resultSet.getInt(4))
                                .precio(resultSet.getDouble(5))
                                .build()
                );
            }


        } catch (SQLException sqlException) {
            throw new UncontrolledException(sqlException);
        }


        return productos;
    }

    @Override
    public Optional<Producto> requestProductoByCodigo(String codigo) throws TiendaException {

        final String SQL_SELECT="SELECT p.nombre, p.descripcion, p.stock, p.precio "+
                                "FROM productos p "+
                                "WHERE p.codigo = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT))
        {ps.setString(1,codigo);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return Optional.of(Producto.builder()
                                    .nombre(rs.getString(1))
                                    .descripcion(rs.getString(2))
                                    .stock(rs.getInt(3))
                                    .precio(rs.getDouble(4))
                                    .codigo(codigo)
                            .build());
                }return Optional.empty();
            }

        } catch (SQLException sqlException) {
                throw new UncontrolledException(sqlException);
        }


    }

    @Override
    public Producto addProducto(Producto producto) throws TiendaException {

        final String SQL_INSERT=" INSERT INTO productos (codigo, nombre, descripcion, stock, precio) "+
                                 " VALUES (nextval('seq_productos'), ? , ? , ? , ? )";

        String[] fields = {"codigo"};

        try(Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, fields))
        {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getStock());
            ps.setDouble(4, producto.getPrecio());

            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys())
            {
                generatedKeys.next();
                   return producto.toBuilder()
                           .codigo(generatedKeys.getString(1))
                           .build();

            }
        } catch (SQLException sqlException) {
            throw new UncontrolledException(sqlException);
        }
    }

    @Override
    public Optional<Producto> replaceProducto(Producto producto) throws TiendaException {
        final String SQL_UPDATE="UPDATE productos "+
                                "SET nombre = ? , descripcion = ? , stock = ?, precio = ? "+
                                "WHERE codigo = ?";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE)){
            ps.setString(1,producto.getNombre());
            ps.setString(2,producto.getDescripcion());
            ps.setInt(3, producto.getStock());
            ps.setDouble(4, producto.getPrecio());
            ps.setString(5,producto.getCodigo());
            int count = ps.executeUpdate();
            return (count==0)? Optional.empty():Optional.of(producto);

        } catch (SQLException sqlException) {
        throw manageException(sqlException,producto);
        }
    }

    @Override
    public void deleteProducto(String codigo) throws TiendaException {
        final String SQL_DELETE= "DELETE FROM productos "+
                                 "WHERE codigo = ?";
        try (Connection connection= dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE)){
            ps.setString(1,codigo);
            int count = ps.executeUpdate();
            if (count ==0) throw new ProductoNotFoundException(codigo);
        } catch (SQLException sqlException) {
            throw new UncontrolledException(sqlException);
        }
    }


    public TiendaException manageException(SQLException sqlException, Producto producto){

        String message = sqlException.getMessage();

        if(message.contains("UK_PRODUCTOS.NOMBRE")) return new EntityExistException("Producto", "Nombre", producto.getNombre());
        else if (message.contains("NN_PRODUCTOS.CODIGO")) return new NotNullFieldException("Poducto", "codigo");
        else if (message.contains("NN_PRODUCTOS.EMAIL")) return new NotNullFieldException("Producto", "email");
        else if (message.contains("NN_PRODUCTOS.NOMBRE")) return new NotNullFieldException("Producto", "nombre");



        return new UncontrolledException(sqlException);
    }
}
