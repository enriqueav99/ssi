package es.upsa.dasi.exceptions;

public class IncorrectFieldEntityException extends TiendaException
{
    String entity;
    String field;
    Object value;

    public static IncorrectFieldEntityException of(String entity, String field, Object value)
    {
        return new IncorrectFieldEntityException(entity, field, value);
    }

    public IncorrectFieldEntityException(String entity, String field, Object value)
    {
        super( String.format("El valor [%s] en la propiedad [%s] de la entidad [%s] es incorrecto", value, field, value.toString()));
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
