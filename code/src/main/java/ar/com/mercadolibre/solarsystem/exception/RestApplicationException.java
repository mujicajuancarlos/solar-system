package ar.com.mercadolibre.solarsystem.exception;

/**
 * Runtime exception for rest application
 */
public abstract class RestApplicationException extends RuntimeException {

    private final String errorCode;
    private final String message;
    private final String devMessage;

    public RestApplicationException(String errorCode, String message, String devMessage) {
        this.errorCode = errorCode;
        this.message = message;
        this.devMessage = devMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getDevMessage() {
        return devMessage;
    }
}
