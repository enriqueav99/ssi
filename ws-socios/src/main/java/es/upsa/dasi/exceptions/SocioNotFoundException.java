package es.upsa.dasi.exceptions;

public class SocioNotFoundException extends TiendaException {
    private String codigo;

    public SocioNotFoundException(String codigo)
    {
        super("No existe un socio con código " + codigo);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

}
