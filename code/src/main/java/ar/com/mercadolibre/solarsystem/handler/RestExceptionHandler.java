package ar.com.mercadolibre.solarsystem.handler;

import ar.com.mercadolibre.solarsystem.dto.ApiErrorDTO;
import ar.com.mercadolibre.solarsystem.exception.EntityNotFoundException;
import ar.com.mercadolibre.solarsystem.exception.RestApplicationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.StringUtils.capitalize;

/**
 * Esta clase maneja las excepciones y responde con un @{@link ApiErrorDTO}
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger HANDLER_LOGGER = LogManager.getLogger();

    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ApiErrorDTO.Builder.create(), BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ApiErrorDTO.Builder builder = ApiErrorDTO.Builder.create()
                .withMessage("Solicitud invalida.")
                .withDevMessage(String.format("%s is not valid", capitalize(ex.getParameter().getParameterName())));
        return handleExceptionInternal(ex, builder, BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final ApiErrorDTO.Builder builder = ApiErrorDTO.Builder.create()
                .withMessage("Solicitud invalida.");
        return handleExceptionInternal(ex, builder, BAD_REQUEST, request);
    }

    /**
     * BAD_REQUEST 400
     */
    @ExceptionHandler({RestApplicationException.class})
    public ResponseEntity<ApiErrorDTO> handleBadRequest(RestApplicationException ex) {
        return handleExceptionInternal(ex, BAD_REQUEST);
    }

    /**
     * NOT_FOUND 404
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<ApiErrorDTO> handleNotFound(EntityNotFoundException ex) {
        return handleExceptionInternal(ex, NOT_FOUND);
    }

    /**
     * SERVER_ERROR 500
     */
    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        final ApiErrorDTO.Builder builder = ApiErrorDTO.Builder.create()
                .withMessage("Error crítico.");
        return handleExceptionInternal(ex, builder, null, INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> handleExceptionInternal(
            Exception ex, ApiErrorDTO.Builder builder, HttpStatus status, WebRequest request) {
        final ApiErrorDTO body = builder
                .withStatus(status)
                .withThrowable(ex)
                .build();
        return handleExceptionInternal(ex, body, null, status, request);
    }

    private ResponseEntity<ApiErrorDTO> handleExceptionInternal(
            RestApplicationException ex, HttpStatus status) {
        final ApiErrorDTO.Builder builder = ApiErrorDTO.Builder.create()
                .withStatus(status)
                .withErrorCode(ex.getErrorCode())
                .withMessage(ex.getMessage())
                .withDevMessage(ex.getDevMessage())
                .withThrowable(ex);
        return new ResponseEntity<>(builder.build(), status);
    }
}
