package es.upsa.dasi.resources;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.resources.providers.beans.ErrorMessage;
import es.upsa.dasi.services.ProductosService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.awt.*;
import java.util.List;


@Tag(name = "productos")
@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
public class ProductosResource {

    @Inject
    ProductosService service;
    @Inject
    Uris uris;

    @Context
    UriInfo uriInfo;




    @Operation(operationId = "getProductos",
            summary = "Acceso a los datos de todos los productos registrados",
            description = "Devuelve los datos de todos los productos registrados en el sistema"
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Se devuelve los datos de todos los productos",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.ARRAY,
                                    implementation = Producto.class
                            )
                    )
            ),
    })
    @GET
    public Response getProductos() throws TiendaException {

        return Response.ok()
                .entity(new GenericEntity<List<Producto>>( service.requestProductos() ) {})
                .build();
    }







    @Operation(operationId = "getProductoByCodigo",
            summary = "Acceso a los datos de un producto identificado por su código",
            description = "Devuelve los datos del producto identificado a través de su codigo"
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Se ha localizado el producto y se devuelven sus datos",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Producto.class
                            )
                    )
            ),
            @APIResponse(responseCode = "404",
                    description = "No hay registrado un producto con el codigo especificado"
            )
    })
    @GET
    @Path("/{codigo}")
    public Response getProductoByCodigo(@Parameter(required = true,
                                                   description = "Código del producto",
                                                   in = ParameterIn.PATH,
                                                   name = "codigo",
                                                   schema = @Schema(type = SchemaType.STRING)
                                        )
                                         @PathParam("codigo") String codigo) throws TiendaException
    {

        return service.getProductoByCodigo(codigo)
                .map(a -> Response.ok().entity(a).build())
                .orElseGet(() ->Response.status(Response.Status.NOT_FOUND).build());
    }









    @Operation(operationId = "addProducto",
            description = "Crea un nuevo producto",
            summary = "Registra un nuevo producto en el sistema"
    )
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Se ha creado el producto correctamente. Se devuelve sus datos entre los que se incluye su código",
                    headers = @Header(name = HttpHeaders.LOCATION,
                            description = "URI con la que acceder al producto que se ha creado",
                            schema = @Schema(type = SchemaType.STRING,
                                    format = "uri"
                            )
                    ),
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Producto.class
                            )
                    )
            ),

            @APIResponse(responseCode = "500",
                    description = "Se ha producido un error",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = ErrorMessage.class)
                    )
            )
    })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProducto(@RequestBody(description = "datos del producto",
                                             required = true,
                                             content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                                                schema = @Schema(type = SchemaType.OBJECT,
                                                                                  implementation = UnidentifiedProducto.class
                                                                                )
                                                               )
                                            )
                                 UnidentifiedProducto uProducto) throws TiendaException
    {
         Producto producto = service.addProducto(uProducto);
        return Response.created(uris.gerUriForProducto(uriInfo, producto))
                .entity(producto)
                .build();
    }











    @Operation(operationId = "replaceProducto",
            description = "Modifica los datos de un producto",
            summary = "Modifica los datos del producto identificado por su codigo"
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Se ha modificado los datos del producto",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Producto.class
                            )
                    )
            ),

            @APIResponse(responseCode = "404",
                    description = "No existe el producto identificado por el codigo indicado"
            ),

            @APIResponse(responseCode = "500",
                    description = "Se ha producido un error",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = ErrorMessage.class
                            )
                    )
            )
    })
    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replaceProducto(@Parameter(required = true,
                                                description = "Codigo del producto",
                                                in = ParameterIn.PATH,
                                                name = "codigo",
                                                schema = @Schema(type = SchemaType.STRING)
                                              )

                                    @PathParam("codigo") String codigo,

                                    @RequestBody(description = "datos del producto",
                                                    required = true,
                                                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                                            schema = @Schema(type = SchemaType.OBJECT,
                                                                              implementation = UnidentifiedProducto.class
                                                                            )
                                                                      )
                                                )

                                    UnidentifiedProducto uProducto)throws TiendaException
    {

        return service.replaceProducto(uProducto,codigo)
                .map( a -> Response.created(uris.gerUriForProducto(uriInfo, a)).entity(a).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());

    }




    @Operation(operationId = "deleteProducto",
            description = "Elimina un producto",
            summary = "Elimina el producto identificado por su codigo"
    )
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "Se ha eliminado el producto correctamente"
            ),

            @APIResponse(responseCode="404",
                    description="No existe el producto identificado por ese codigo"
            )
    })
    @DELETE
    @Path("{codigo}")
    public Response deleteProducto(@Parameter(required = true,
                                              description = "Codigo del producto",
                                              in = ParameterIn.PATH,
                                              name = "codigo",
                                              schema = @Schema(type = SchemaType.STRING)
                                             )
                                   @PathParam("codigo") String codigo) throws TiendaException {

        if (service.deleteProducto(codigo)){
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
