package es.upsa.dasi.daos.providers;

import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.daos.providers.beans.ErrorMessage;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.Response;

public class TiendaResponseExceptionMapper implements ResponseExceptionMapper<TiendaException> {
    @Override
    public TiendaException toThrowable(Response response) {
        ErrorMessage errorMessage = response.readEntity(ErrorMessage.class);
        return new TiendaException( errorMessage.getMessage() );
    }
}
