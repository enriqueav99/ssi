package es.upsa.dasi.exceptions;

public class NotNullFieldException extends TiendaException{
    String entityName;
    String fieldName;

    public static NotNullFieldException of(String entityName, String fieldName)
    {
        return new NotNullFieldException(entityName, fieldName);
    }

    public NotNullFieldException(String entityName, String fieldName)
    {
        super( String.format("Es obligatorio especificar un valor para la propiedad [%s] de %s", fieldName, entityName) );
        this.entityName = entityName;
        this.fieldName = fieldName;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
