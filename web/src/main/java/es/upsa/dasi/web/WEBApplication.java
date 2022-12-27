package es.upsa.dasi.web;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.krazo.Properties;

import java.util.Map;

@ApplicationPath("/api")
public class WEBApplication extends Application
{

    @Override
    public Map<String, Object> getProperties() {

        return Map.of(Properties.HIDDEN_METHOD_FILTER_ACTIVE, true);
    }

}
