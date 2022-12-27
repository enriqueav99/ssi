package es.upsa.dasi.resources;

import es.upsa.dasi.model.Producto;
import es.upsa.dasi.model.Socio;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@ApplicationScoped
public class Uris {

    public URI getUriForSocio(UriInfo uriInfo, Socio socio){
        return uriInfo.getBaseUriBuilder()
                .path("/socios/{codigo}")
                .resolveTemplate("codigo", socio.getCodigo())
                .build();
    }

    public URI gerUriForProducto(UriInfo uriInfo, Producto producto){
        return uriInfo.getBaseUriBuilder()
                .path("/productos/{codigo}")
                .resolveTemplate("codigo", producto.getCodigo())
                .build();
    }



}
