package es.upsa.dasi.exceptions;

public class TiendaException extends Exception{
    public TiendaException() {
    }

    public TiendaException(String message) {
        super(message);
    }

    public TiendaException(String message, Throwable cause) {
        super(message, cause);
    }

    public TiendaException(Throwable cause) {
        super(cause);
    }

    public TiendaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
