package es.upsa.dasi.exceptions;

public class ForeignKeyException extends TiendaException{
    String entityName;
    String fieldName;
    Object fieldValue;
    Class fieldClass;
    String referencedEntityName;

    public static <T> ForeignKeyException of(String entityName, String fieldName, T fieldValue, Class<T> fieldClass, String referencedEntityName)
    {
        return new ForeignKeyException(entityName, fieldName, fieldValue, fieldClass, referencedEntityName);
    }

    private ForeignKeyException(String entityName, String fieldName, Object fieldValue, Class fieldClass, String referencedEntityName)
    {
        super( "" );
        this.entityName = entityName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.fieldClass = fieldClass;
        this.referencedEntityName = referencedEntityName;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public Class getFieldClass() {
        return fieldClass;
    }

    public String getReferencedEntityName() {
        return referencedEntityName;
    }
}
