package refactored.chunim.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import refactored.chunim.handler.exceptions.ExceptionDetails;

import javax.ws.rs.BadRequestException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)

    public ResponseEntity<ExceptionDetails> handlerExceptionDetails(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, please check the Documentation")
                        .devMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
