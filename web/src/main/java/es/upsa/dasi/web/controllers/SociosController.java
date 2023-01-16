package es.upsa.dasi.web.controllers;

import es.upsa.dasi.dtos.UnidentifiedSocio;
import es.upsa.dasi.exceptions.TiendaException;
import es.upsa.dasi.model.Socio;
import es.upsa.dasi.web.controllers.beans.Operacion;
import es.upsa.dasi.web.controllers.beans.SocioBean;
import es.upsa.dasi.web.services.SociosService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.MvcContext;
import jakarta.mvc.UriRef;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.*;


import java.util.*;

@Path("{idioma}/socios")
public class SociosController {
    @PathParam("idioma")
    String idioma;

    @Inject
    SociosService service;

    @Inject
    MvcContext mvc;

    @Inject
    Models models;

    @Inject
    Validator validator;

    @GET
    @Controller
    @UriRef("allSocios")
    public Response requestAllSocios() throws TiendaException {
        List<Socio> socios = service.requestAllSocios();

        models.put("socios", socios);
        return Response.ok()
                .entity("/jsps/socios.jsp")
                .build();
    }

    @GET
    @Controller
    @UriRef("oneSocio")
    @Path("/{codigo}")
    public Response requestSocioByCodigo(@PathParam("codigo") String codigo) throws TiendaException {
        Optional<Socio> socio = service.requestSocioByCodigo(codigo);
        if(socio.isPresent()){
           models.put("socio", socio.get() );
            return Response.ok()
                    .entity("/jsps/socio.jsp")
                    .build();
       }
        return Response.ok()
                .entity("/jsps/socioNotFound.jsp")
                .build();



    }

    @POST
    @Controller
    @UriRef("insertSocio")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response requestAddSocio(@BeanParam SocioBean socioBean) throws TiendaException {

        Set<ConstraintViolation<SocioBean>> violations = validator.validate(socioBean);
        if (violations.isEmpty()) {
            UnidentifiedSocio uSocio = UnidentifiedSocio.builder()
                    .nombre(socioBean.getNombre())
                    .email(socioBean.getEmail())
                    .build();

            Socio socio = service.requestAddSocio(uSocio);

            return Response
                    .seeOther(mvc.uri("oneSocio", Map.of("codigo", socio.getCodigo(), "idioma", mvc.getLocale().getLanguage())))
                    .build();
        }

        List<String> errores = new ArrayList<>();
        for (ConstraintViolation<SocioBean> violation : violations) {
            errores.add(violation.getMessage());
        }

        models.put("errores", errores);
        models.put("socio", socioBean);
        models.put("operacion", Operacion.INSERT);
        return Response.ok()
                .entity("/jsps/formularioSocio.jsp")
                .build();

    }


    @PUT
    @Controller
    @UriRef("updateSocio")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/update/{codigo}")
    public Response requestUpdateSocio(@PathParam("codigo") String codigo,
                                       @BeanParam SocioBean socioBean ) throws TiendaException {

        Set<ConstraintViolation<SocioBean>> violations = validator.validate(socioBean);

        if(violations.isEmpty()){
            Optional<Socio> socio = service.requestUpdateSocio(Socio.builder()
                    .codigo(codigo)
                    .nombre(socioBean.getNombre())
                    .email(socioBean.getEmail())
                    .saldo(socioBean.getSaldoAsDouble())
                    .build());


            if (socio.isPresent()) {
                return Response.seeOther(mvc.uri("allSocios", Map.of("idioma", mvc.getLocale().getLanguage()))).build();
            } else {
                return Response.ok().entity("/jsps/socioNotFound.jsp").build();
            }
        }
        List<String> errores = new ArrayList<>();
        for (ConstraintViolation<SocioBean> violation : violations) {
            errores.add(violation.getMessage());
        }
        models.put("errores", errores);
        models.put("socio", socioBean);
        models.put("operacion", Operacion.UPDATE);
        return Response.ok()
                .entity("/jsps/formularioSocio.jsp")
                .build();

    }


    @DELETE
    @Controller
    @UriRef("deleteSocio")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/delete/{codigo}")
    public Response requestDeleteSocio(@PathParam("codigo") String codigo) throws TiendaException {

        service.requestDeleteSocio(codigo);
        return Response.seeOther(mvc.uri("allSocios", Map.of("idioma", mvc.getLocale().getLanguage()))).build();


    }

}
