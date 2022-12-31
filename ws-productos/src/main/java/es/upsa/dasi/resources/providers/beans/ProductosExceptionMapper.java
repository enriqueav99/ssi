package es.upsa.dasi.resources.providers.beans;

import es.upsa.dasi.exceptions.ProductoNotFoundException;
import es.upsa.dasi.exceptions.TiendaException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProductosExceptionMapper implements ExceptionMapper<TiendaException> {
    @Override
    public Response toResponse(TiendaException exception) {
        if ( ProductoNotFoundException.class.isInstance( exception ) ) produceResponse( ProductoNotFoundException.class.cast(exception) );
        return produceDefault( exception );
    }

    public Response produceDefault(TiendaException exception)
    {
        return Response.serverError()
                .entity(ErrorMessage.builder()
                        .withCode(500)
                        .withMessage( exception.getMessage() )
                        .build()
                )
                .build();
    }

    public Response produceResponse(TiendaException exception)
    {
        return Response.status(Response.Status.NOT_FOUND)
                .entity( ErrorMessage.builder()
                        .withCode(404)
                        .withMessage(exception.getMessage() )
                        .build()
                )
                .build();
    }
}

