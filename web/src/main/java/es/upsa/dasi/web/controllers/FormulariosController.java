package es.upsa.dasi.web.controllers;

import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.web.controllers.beans.Operacion;
import es.upsa.dasi.web.services.ProductosService;
import es.upsa.dasi.web.services.SociosService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.Optional;

@Path("{idioma}/formularios")
public class FormulariosController {
    @PathParam("idioma")
    String idioma;

    @Inject
    Models models;

    @Inject
    SociosService service;

    @Inject
    ProductosService productosService;

    @GET
    @Controller
    @UriRef("formularioInsertSocio")
    public String requestFormularioAddAsignatura(){
        models.put("operacion", Operacion.INSERT);
        return "/jsps/formularioSocio.jsp";
    }

    @GET
    @Controller
    @UriRef("formularioUpdateSocio")
    @Path("/socios/{codigo}")
    public String requestFormularioUpdateAsignatura(@PathParam("codigo") String codigo) throws TiendaException {
        Optional<Socio> optSocio = service.requestSocioByCodigo(codigo);

        if (optSocio.isPresent()){
            models.put("operacion", Operacion.UPDATE);
            models.put("socio", optSocio.get());
            return "/jsps/formularioSocio.jsp";
        }


        return "/jsps/socioNotFound.jsp";
    }


    @GET
    @Controller
    @UriRef("formularioDeleteSocio")
    @Path("/socios/borrar/{codigo}")
    public String requestFormularioDeleteSocio(@PathParam("codigo") String codigo) throws TiendaException {

        Optional<Socio> optSocio = service.requestSocioByCodigo(codigo);

        if (optSocio.isPresent()){
            models.put("operacion", Operacion.DELETE);
            models.put("socio", optSocio.get());
            return "/jsps/formularioSocio.jsp";
        }


        return "/jsps/socioNotFound.jsp";

    }


    @GET
    @Controller
    @UriRef("formularioInsertProducto")
    @Path("/productos/insert")
    public String requestFormularioInsertProducto(){
        models.put("operacion",Operacion.INSERT );
        return "/jsps/formularioProducto.jsp";
    }

    @GET
    @Controller
    @UriRef("formularioUpdateProducto")
    @Path("/productos/update/{codigo}")
    public String requestFormularioUpdateProducto(@PathParam("codigo")String codigo) throws TiendaException {
        Optional<Producto> producto = productosService.requestProducto(codigo);
        if(producto.isPresent()) {
            models.put("operacion", Operacion.UPDATE);
            models.put("producto",producto.get());
            return "/jsps/formularioProducto.jsp";
        }
        return "/jsps/productoNotFound";
    }

    @GET
    @Controller
    @UriRef("formularioDeleteProducto")
    @Path("/productos/delete/{codigo}")
    public String requestFormularioDelteProducto(@PathParam("codigo")String codigo) throws TiendaException {
        Optional<Producto> producto = productosService.requestProducto(codigo);
        if(producto.isPresent()) {
            models.put("operacion", Operacion.DELETE);
            models.put("producto",producto.get());
            return "/jsps/formularioProducto.jsp";
        }
        return "/jsps/productoNotFound";
    }
}
