package practica.turismo.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
