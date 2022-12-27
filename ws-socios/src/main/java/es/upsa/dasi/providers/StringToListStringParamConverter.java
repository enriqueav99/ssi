package es.upsa.dasi.providers;

import javax.ws.rs.ext.ParamConverter;
import java.util.List;
import java.util.stream.Collectors;

public class StringToListStringParamConverter implements ParamConverter<List<String>> {
    @Override
    public List<String> fromString(String value)
    {
        return List.of( value.split(",") );
    }

    @Override
    public String toString(List<String> values)
    {
        return values.stream().collect( Collectors.joining(",") );
    }

}
