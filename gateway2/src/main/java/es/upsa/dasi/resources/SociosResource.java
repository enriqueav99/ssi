package es.upsa.dasi.resources;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.resources.providers.beans.ErrorMessage;
import es.upsa.dasi.services.SociosService;
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
import java.util.List;

@Tag(name = "socios")
@Path("/socios")
@Produces(MediaType.APPLICATION_JSON)
public class SociosResource {

    @Inject
    SociosService socioService;

    @Context
    UriInfo uriInfo;

    @Inject
    Uris uris;



    @Operation(operationId = "requestSocios",
            summary = "Acceso a los datos de todos los socios registrados",
            description = "Devuelve los datos de todos los socios registrados en el sistema"
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Se devuelve los datos de todos los socios",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.ARRAY,
                                    implementation = Socio.class
                            )
                    )
            ),
    })
    @GET
    public Response requestSocios() throws TiendaException {

        return Response.ok()
                .entity(new GenericEntity<List<Socio>>( socioService.requestSocios() ) {})
                .build();
    }







    @Operation(operationId = "requestSocioByCodigo",
            summary = "Acceso a los datos de un socio identificado por su codigo",
            description = "Devuelve los datos del socio identificado a través de su codigo"
    )
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Se ha localizado el socio y se devuelven sus datos",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Socio.class
                            )
                    )
            ),
            @APIResponse(responseCode = "404",
                    description = "No hay registrado un socio con el codigo especificado"
            )
    })
    @GET
    @Path("/{codigo}")
    public Response requestSocioByCodigo(@Parameter(required = true,
                                                    description = "Código del socio",
                                                    in = ParameterIn.PATH,
                                                    name = "codigo",
                                                    schema = @Schema(type = SchemaType.STRING)
                                         )
                                          @PathParam("codigo") String codigo) throws TiendaException {


        return socioService.requestSocioByCodigo(codigo)
                .map(a-> Response.ok().entity(a).build())
                .orElseGet(()-> Response.status(Response.Status.NOT_FOUND).build());
    }










    @Operation(operationId = "addSocio",
            description = "Crea un nuevo socio",
            summary = "Registra un nuevo socio en el sistema"
    )
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Se ha creado el socio correctamente. Se devuelve sus datos entre los que se incluye su código",
                    headers = @Header(name = HttpHeaders.LOCATION,
                            description = "URI con la que acceder al socio que se ha creado",
                            schema = @Schema(type = SchemaType.STRING,
                                    format = "uri"
                            )
                    ),
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.OBJECT,
                                    implementation = Socio.class
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
    public Response addSocio(@RequestBody(description = "datos del socio",
                                          required = true,
                                          content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                                             schema = @Schema(type = SchemaType.OBJECT,
                                                                              implementation = UnidentifiedSocio.class
                                                                             )
                                                            )
                                         )
                                 UnidentifiedSocio uSocio)throws TiendaException{

        Socio socio =socioService.addSocio(uSocio);

        return Response.created(uris.getUriForSocio(uriInfo, socio))
                .entity(socio)
                .build();
    }








    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putSocio(Socio socio) throws TiendaException
    {

        return socioService.putSocio(socio)
                .map(s -> Response.created(uris.getUriForSocio(uriInfo, socio)).entity(s).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }










    @Operation(operationId = "deleteSocio",
            description = "Elimina un socio",
            summary = "Elimina el socio identificado por su codigo"
    )
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "Se ha eliminado el socio correctamente"
            ),

            @APIResponse(responseCode="404",
                    description="No existe el socio identificado por ese codigo"
            )
    })
    @DELETE
    @Path("/{codigo}")
    public Response deleteSocio(@Parameter(required = true,
                                            description = "Código del socio",
                                            in = ParameterIn.PATH,
                                            name = "codigo",
                                            schema = @Schema(type = SchemaType.STRING)
                                          )
                                 @PathParam("codigo") String codigo) throws TiendaException {


        if (socioService.deleteSocio(codigo)){
            return Response.ok().build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}