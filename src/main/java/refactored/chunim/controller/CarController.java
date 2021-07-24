package refactored.chunim.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactored.chunim.model.Car;
import refactored.chunim.model.request.CarPostRequestBody;
import refactored.chunim.model.request.CarPutRequestBody;
import refactored.chunim.service.CarService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> listAll() {
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/cars/{id}")
    public ResponseEntity<Car> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @GetMapping(path = "/cars/{name}")
    public ResponseEntity<List<Car>> findByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(carService.findByName(name));
    }

    @PostMapping("cars")
    public ResponseEntity<Car> save(@RequestBody @Valid CarPostRequestBody carPostRequestBody) {
        return new ResponseEntity<>(carService.save(carPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/cars/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("cars")
    public ResponseEntity<Void> update(@Valid @RequestBody CarPutRequestBody carPutRequestBody) {
        carService.update(carPutRequestBody);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
