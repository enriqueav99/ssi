package es.upsa.dasi.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.mvc.locale.LocaleResolver;
import jakarta.mvc.locale.LocaleResolverContext;
import jakarta.ws.rs.core.PathSegment;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class WEBLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(LocaleResolverContext localeResolverContext) {
        UriInfo uriInfo = localeResolverContext.getUriInfo();
        List<PathSegment> pathSegments= uriInfo.getPathSegments();
        PathSegment idiomaPathSegment = pathSegments.get(0);
        String idioma = idiomaPathSegment.getPath();

        return Locale.forLanguageTag(idioma);
    }
}
