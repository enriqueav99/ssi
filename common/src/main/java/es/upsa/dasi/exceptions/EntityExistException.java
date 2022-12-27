package es.upsa.dasi.exceptions;

public class EntityExistException extends TiendaException{
    String entity;
    String field;
    Object value;


    public static EntityExistException of(String entity, String field, Object value)
    {
        return new EntityExistException(entity, field, value);
    }

    public EntityExistException(String entity, String field, Object value)
    {
        super( String.format("Existe un %s con un valor repetido (%s) en la propiedad %s", entity, value.toString(), field));
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

    public String getEntity() {
        return entity;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
