package es.upsa.dasi.providers;
import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Provider
public class ToListParamConverterProvider implements ParamConverterProvider
{
    private StringToListStringParamConverter stringToListStringParamConverter= new StringToListStringParamConverter();



    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations)
    {
        if ( List.class.isAssignableFrom( aClass ) )
        {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class actualTypeArgument = (Class) actualTypeArguments[0];
            if (String.class.isAssignableFrom( actualTypeArgument ))
            {
                return (ParamConverter<T>) stringToListStringParamConverter;
            }
        }
        return null;
    }
}
