package cheanxin.exceptions;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by 273cn on 16/12/22.
 */
public class InvalidArgumentException extends RuntimeException implements ErrorResponseException {
    private ErrorResponse errorResponse;

    public InvalidArgumentException(ObjectError error) {
        errorResponse = new ErrorResponse(error.getCode(), error.getDefaultMessage());
    }

    public InvalidArgumentException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
