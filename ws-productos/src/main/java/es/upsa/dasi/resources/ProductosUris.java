package es.upsa.dasi.resources;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@ApplicationScoped
public class ProductosUris {

    private static final String TEMPLATE_URI_PRODUCTO = "/productos/{codigo}";

    public URI getProductoUri(UriInfo uriInfo, String codigo){
        return uriInfo.getBaseUriBuilder()
                .path(TEMPLATE_URI_PRODUCTO)
                .resolveTemplate("codigo", codigo)
                .build();

    }
}
