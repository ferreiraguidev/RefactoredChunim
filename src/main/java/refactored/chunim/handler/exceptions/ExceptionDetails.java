package refactored.chunim.handler.exceptions;

import lombok.Builder;
import lombok.Data;


@Data
@Builder

public class ExceptionDetails {

    protected String title;
    protected int status;
    protected String details;
    protected String devMessage;

}
