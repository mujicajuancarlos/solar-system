package ar.com.mercadolibre.solarsystem.exception;

import static java.lang.String.format;

public class EntityNotFoundException extends RestApplicationException {

    private static final String ERROR_CODE = "ERROR.ENTITY.NOT.FOUND";
    private static final String ERROR_MESSAGE = "El recurso %s que intenta acceder no existe.";
    private static final String DEVELOPER_MESSAGE = "Resource Not Found Error. The requested resource was not found.";

    public EntityNotFoundException() {
        super(ERROR_CODE, ERROR_MESSAGE, DEVELOPER_MESSAGE);
    }

    public EntityNotFoundException(String format, Object... args) {
        super(ERROR_CODE, format(format, args), DEVELOPER_MESSAGE);
    }

    public EntityNotFoundException(String identifier) {
        this(ERROR_MESSAGE, identifier);
    }
}
