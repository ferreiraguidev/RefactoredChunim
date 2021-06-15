package refactored.chunim.handler;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import refactored.chunim.handler.exceptions.BadRequestException;
import refactored.chunim.handler.exceptions.ExceptionDetails;
import refactored.chunim.handler.exceptions.ValidationExceptionDetails;


import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class RestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)

    public ResponseEntity<ExceptionDetails> handlerExceptionDetails(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, please check the Documentation")
                        .devMessage("This Car does not exist yet in the database")
                        .build(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<ValidationExceptionDetails> handlerMethodNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));

        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Invalid fields")
                        .devMessage(methodArgumentNotValidException.getClass().getName())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .fieldsMessage(fieldsMessage)
                        .fields(fields)
                        .details("Check the fields error")
                        .build(), HttpStatus.BAD_REQUEST);

    }
}

