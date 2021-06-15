package refactored.chunim.endpoint.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class CarController { // DAO *

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> listAll() {
        return new ResponseEntity<>(carService.findAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable Integer id){
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

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody CarPutRequestBody carPutRequestBody) {
        carService.update(carPutRequestBody);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public CarController(CarService carService) {
        this.carService = carService;

    }
}