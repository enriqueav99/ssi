package es.upsa.dasi.web.controllers;

import es.upsa.dasi.dtos.UnidentifiedProducto;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Producto;
import es.upsa.dasi.web.controllers.beans.Operacion;
import es.upsa.dasi.web.controllers.beans.ProductoBean;
import es.upsa.dasi.web.services.ProductosService;
import es.upsa.dasi.web.services.SociosService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.MvcContext;
import jakarta.mvc.UriRef;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("/{idioma}/productos")
public class ProductosController {


    @PathParam("idioma")
    String idioma;

    @Inject
    ProductosService service;

    @Inject
    MvcContext mvc;

    @Inject
    Models models;

    @Inject
    Validator validator;


    @GET
    @Controller
    @UriRef("allProductos")
    public Response requestProductos() throws TiendaException {

        List<Producto> productos = service.requestProductos();
        models.put("productos", productos);
        return Response
                .ok()
                .entity("/jsps/productos.jsp")
                .build();
    }

    @GET
    @Path("/{codigo}")
    @Controller
    @UriRef("oneProducto")
    public Response requestProducto(@PathParam("codigo") String codigo) throws TiendaException {
        Optional<Producto> producto = service.requestProducto(codigo);
        if (producto.isPresent()){
            models.put("producto", producto.get());
            return Response.ok().entity("/jsps/producto.jsp").build();
        }
        return Response.ok().entity("/jsps/productoNotFound.jsp").build();
    }

    @POST
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UriRef("insertProducto")
    public Response requestAddProducto(@BeanParam ProductoBean productoBean) throws TiendaException {
        Set<ConstraintViolation<ProductoBean>> violations = validator.validate(productoBean);
        if (violations.isEmpty()) {
            Producto producto = service.requestAddProducto(UnidentifiedProducto.builder()
                    .nombre(productoBean.getNombre())
                    .descripcion(productoBean.getDescripcion())
                    .stock(productoBean.getStockAsInt())
                    .precio(productoBean.getPrecioAsDouble())
                    .build());
            return Response
                    .seeOther(mvc.uri("oneProducto",
                            Map.of("idioma", mvc.getLocale().getLanguage(), "codigo", producto.getCodigo())))
                    .build();
        }
        List<String> errores = new ArrayList<>();

        for (ConstraintViolation<ProductoBean> violation : violations) {
            errores.add(violation.getMessage());
        }
        models.put("operacion", Operacion.INSERT);
        models.put("producto", productoBean );
        models.put("errores", errores);
        return Response.ok("/jsps/formularioProducto.jsp")
                .build();
    }

    @PUT
    @Controller
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UriRef("updateProducto")
    public Response requestUpdateProducto(@BeanParam ProductoBean productoBean, @PathParam("codigo") String codigo) throws TiendaException {

        Set<ConstraintViolation<ProductoBean>> violations = validator.validate(productoBean);
        if (violations.isEmpty()) {
            return service.requestUpdateProducto(UnidentifiedProducto.builder()
                            .nombre(productoBean.getNombre())
                            .descripcion(productoBean.getDescripcion())
                            .precio(productoBean.getPrecioAsDouble())
                            .stock(productoBean.getStockAsInt())
                            .build(), codigo)
                    .map(a -> Response.seeOther(mvc.uri("allProductos", Map.of("idioma", mvc.getLocale().getLanguage()))).build())
                    .orElseGet(() -> Response.ok().entity("/jsps/productoNotFound.jsp").build());
        }

        List<String> errores = new ArrayList<>();

        for (ConstraintViolation<ProductoBean> violation : violations) {
            errores.add(violation.getMessage());
        }
        models.put("operacion", Operacion.UPDATE);
        models.put("producto", Producto.builder()
                .codigo(codigo)
                .nombre(productoBean.getNombre())
                .descripcion(productoBean.getDescripcion())
                .precio(productoBean.getPrecioAsDouble())
                .stock(productoBean.getStockAsInt())
                .build() );
        models.put("errores", errores);
        return Response.ok("/jsps/formularioProducto.jsp")
                .build();
    }

    @DELETE
    @Controller
    @UriRef("deleteProducto")
    @Path("/{codigo}")
    public Response requestDelteProducto(@PathParam("codigo")String codigo) throws TiendaException {

        if (service.requestDelteProducto(codigo)){
            return Response.seeOther(mvc.uri("allProductos", Map.of("idioma", mvc.getLocale().getLanguage()))).build();
        }

        return Response.seeOther(mvc.uri("/jsps//productoNotFound.jsp")).build();
    }
}
