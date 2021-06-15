package refactored.chunim.endpoint.Util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class EndpointUtil implements Serializable {

    public ResponseEntity<?> returnObjectOrThrowBotFound(Object object){
        return  object == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(object , HttpStatus.NOT_FOUND);

    }

}
