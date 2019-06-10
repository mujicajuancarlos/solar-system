package ar.com.mercadolibre.solarsystem.exception;

import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BusinessValidationException extends RestApplicationException {

    private static final String ERROR_CODE = "ERROR.BUSINESS.VALIDATION";
    private static final String ERROR_MESSAGE = "Error! Su solicitud no cumple con las reglas de negocio.";
    private static final String DEV_MESSAGE = "The data passed in the request was invalid.";

    private List<? extends FieldError> errors = new ArrayList<>();

    public BusinessValidationException() {
        super(ERROR_CODE, ERROR_MESSAGE, DEV_MESSAGE);
    }

    public BusinessValidationException(String errorCode, String message) {
        super(errorCode, message, DEV_MESSAGE);
    }

    public BusinessValidationException(List<? extends FieldError> fieldErrors) {
        this();
        errors = fieldErrors;
    }

    public BusinessValidationException(Set<? extends ConstraintViolation<?>> violations) {
        this(getFieldErrors(violations));
    }

    private static List<FieldError> getFieldErrors(Set<? extends ConstraintViolation<?>> violations) {
        final List<FieldError> errors = new ArrayList<>();
        for (ConstraintViolation<?> constraintViolation : violations) {
            final String objectName = constraintViolation.getRootBeanClass().getSimpleName();
            final String field = constraintViolation.getPropertyPath().toString();
            final Object rejectedValue = constraintViolation.getInvalidValue() != null ? constraintViolation.getInvalidValue().toString()
                    : null;
            final String[] codes = {constraintViolation.getMessageTemplate()};
            final String defaultMessage = constraintViolation.getMessage();
            final FieldError fieldError = new FieldError(objectName, field, rejectedValue, false, codes, null, defaultMessage);
            errors.add(fieldError);
        }
        return errors;
    }

    public List<? extends FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<? extends FieldError> errors) {
        this.errors = errors;
    }

}
