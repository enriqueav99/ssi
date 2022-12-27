package es.upsa.dasi.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class EntityNotFoundException  extends TiendaException{
    public static EntityNotFoundException of(String entity, List<String> fields, List<Object> values)
    {
        return new EntityNotFoundException(entity, fields, values);
    }

    String entity;
    List<String> fields;
    List<Object> values;



    public EntityNotFoundException(String entity, List<String> fields, List<Object> values) {
        super(String.format("No existe [%s] con los valores [%s] en las propiedades [%s]",
                entity,
                values.stream().map(Object::toString).collect(Collectors.joining(",")),
                fields.stream().collect(Collectors.joining(","))));
        this.entity = entity;
        this.fields = fields;
        this.values = values;
    }

    public String getEntity() {
        return entity;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<Object> getValues() {
        return values;
    }
}
