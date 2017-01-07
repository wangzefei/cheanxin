package cheanxin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;

/**
 * Created by 273cn on 16/12/22.
 */
public class UnauthorizedException extends RuntimeException implements ErrorResponseException {
    private ErrorResponse errorResponse;

    public UnauthorizedException(ObjectError error) {
        errorResponse = new ErrorResponse(error.getCode(), error.getDefaultMessage());
    }

    public UnauthorizedException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public UnauthorizedException(String username) {
        this.errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.getReasonPhrase(), "User " + username + " unauthorized.");
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
