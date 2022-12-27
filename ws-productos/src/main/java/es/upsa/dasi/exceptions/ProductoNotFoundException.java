package es.upsa.dasi.exceptions;

public class ProductoNotFoundException extends TiendaException{
    private String codigo;

    public ProductoNotFoundException(String codigo){
        super("No existe un producto con código " + codigo);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
