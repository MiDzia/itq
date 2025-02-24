package by.walker.orders.exception.handler;

import by.walker.orders.exception.BadRequestException;
import by.walker.orders.exception.InternalServerError;
import by.walker.orders.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleBadRequestException(RuntimeException runTimeEx){
        return new ErrorResponseDto(runTimeEx.getMessage());
    }

    @ExceptionHandler(InternalServerError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto handleInternalServerError(RuntimeException runTimeEx){
        return new ErrorResponseDto(runTimeEx.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto handleOrderNotFoundException(RuntimeException runTimeEx){
        return new ErrorResponseDto(runTimeEx.getMessage());
    }
}
