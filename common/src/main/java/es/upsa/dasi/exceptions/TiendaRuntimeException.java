package es.upsa.dasi.exceptions;

public class TiendaRuntimeException extends RuntimeException{
    public TiendaRuntimeException() {
    }

    public TiendaRuntimeException(String message) {
        super(message);
    }

    public TiendaRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TiendaRuntimeException(Throwable cause) {
        super(cause);
    }

    public TiendaRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
