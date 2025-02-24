package by.walker.orders.exception;

import by.walker.orders.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(ErrorMessage message) {
        super(String.valueOf(message));
    }
}
