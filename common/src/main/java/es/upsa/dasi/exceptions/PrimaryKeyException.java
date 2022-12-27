package es.upsa.dasi.exceptions;

import java.util.List;

public class PrimaryKeyException extends TiendaException
{
    public static PrimaryKeyException of(String entityName, List<String> fields, List<Object> values)
    {
        return new PrimaryKeyException(entityName, fields, values);
    }

    String entityName;
    List<String> fields;
    List<Object> values;

    public PrimaryKeyException(String entityName, List<String> fields, List<Object> values) {
        super("PRIMARY KEY");
        this.entityName = entityName;
        this.fields = fields;
        this.values = values;
    }

    public String getEntityName() {
        return entityName;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<Object> getValues() {
        return values;
    }
}
