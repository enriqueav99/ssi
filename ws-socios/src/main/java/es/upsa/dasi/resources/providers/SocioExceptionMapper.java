package es.upsa.dasi.resources.providers;

import es.upsa.dasi.exceptions.SocioNotFoundException;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.resources.providers.beans.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SocioExceptionMapper implements ExceptionMapper<TiendaException> {
    @Override
    public Response toResponse(TiendaException exception) {
        if ( SocioNotFoundException.class.isInstance( exception ) ) produceResponse( SocioNotFoundException.class.cast(exception) );
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


