package ar.com.mercadolibre.solarsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

import static java.util.Optional.ofNullable;

public class ApiErrorDTO implements Serializable {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private HttpStatus status;

    @JsonProperty("error_code")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String errorCode;

    @JsonProperty("message")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String message;

    @JsonProperty("dev_message")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String devMessage;

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public String getDevMessage() {
        return devMessage;
    }


    public ApiErrorDTO() {
    }

    public static final class Builder {

        private static final String DEFAULT_MESSAGE = "Error inesperado";
        private static final String DEFAULT_DEV_MESSAGE = "Server Error";

        private HttpStatus status;
        private String errorCode;
        private String message;
        private String devMessage;
        private Throwable throwable;

        public static Builder create() {
            return new Builder();
        }

        public Builder withStatus(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder withErrorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withDevMessage(String devMessage) {
            this.devMessage = devMessage;
            return this;
        }

        public Builder withThrowable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        public ApiErrorDTO build() {
            ApiErrorDTO dto = new ApiErrorDTO();
            dto.status = ofNullable(this.status).isPresent() ? this.status : HttpStatus.INTERNAL_SERVER_ERROR;
            dto.errorCode = ofNullable(this.errorCode).isPresent() ? this.errorCode : "ERROR.INTERNAL";
            dto.message = ofNullable(this.message).isPresent() ? this.message : DEFAULT_MESSAGE;
            dto.devMessage = ofNullable(this.throwable).isPresent() ? this.throwable.getLocalizedMessage() : null;
            dto.devMessage = ofNullable(this.devMessage).isPresent() ? this.devMessage : DEFAULT_DEV_MESSAGE;
            return dto;
        }
    }
}
