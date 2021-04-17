package refactored.chunim.endpoint.controller;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import refactored.chunim.endpoint.service.CarService;
import refactored.chunim.model.Car;
import refactored.chunim.request.CarPostRequestBody;
import refactored.chunim.request.CarPutRequestBody;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("cars")
@Log4j2
@Data
public class CarController { // DAO *
    // return a json value

    // Endpoints will be here

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listAll() {
        return new ResponseEntity<>(carService.findAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable Integer id){

        return ResponseEntity.ok(carService.findById(id));
    }

    @GetMapping(path = "/by-id{id}")
    public ResponseEntity<Car> findByIdAuthentication(@PathVariable Integer id,
                                                      @AuthenticationPrincipal UserDetails userDetails) {
        log.info(userDetails);
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody @Valid CarPostRequestBody carPostRequestBody) {
        return new ResponseEntity<>(carService.save(carPostRequestBody), HttpStatus.CREATED);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Car> delete(@PathVariable Integer id){
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     *
     * @param carPutRequestBody
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CarPutRequestBody carPutRequestBody) {
        carService.update(carPutRequestBody);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public CarController(CarService carService) {
        this.carService = carService;
    }
}
