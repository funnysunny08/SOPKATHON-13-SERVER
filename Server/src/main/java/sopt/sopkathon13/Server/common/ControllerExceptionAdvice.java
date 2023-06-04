package sopt.sopkathon13.Server.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sopt.sopkathon13.Server.common.dto.ApiResponse;
import sopt.sopkathon13.Server.exception.Error;
import sopt.sopkathon13.Server.exception.model.NotFoundException;
import sopt.sopkathon13.Server.exception.model.SoptException;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    /**
     * 404 Internal Server
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    protected ApiResponse<Object> handleException(final NotFoundException e) {
        return ApiResponse.error(Error.NOT_FOUND);
    }

    /**
     * Sopt custom error
     */
    @ExceptionHandler(SoptException.class)
    protected ResponseEntity<ApiResponse> handleSoptException(SoptException e) {
        return ResponseEntity.status(e.getHttpStatus())
                .body(ApiResponse.error(e.getError(), e.getMessage()));
    }
}