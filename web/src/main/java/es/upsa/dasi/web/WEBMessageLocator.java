package es.upsa.dasi.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@ApplicationScoped
@Named("messageLocator")
public class WEBMessageLocator {
    public String getMesagge(String baseName, String clave, Locale locale){
        ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
        return bundle.getString(clave);
    }

    public String format(String message, String... params){
        return MessageFormat.format(message, params);
    }
}
