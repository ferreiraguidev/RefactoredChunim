package refactored.chunim.endpoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/cars")
public class AdminController {

    @GetMapping
    public ResponseEntity<?> Hallo() {
        return new ResponseEntity<>("Hallo", HttpStatus.OK);
    }
}
