package refactored.chunim.handler.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder

public class ExceptionDetails {

    protected String title;
    protected int status;
    protected String details;
    protected String devMessage;

}
