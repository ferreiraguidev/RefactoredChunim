package refactored.chunim.endpoint;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import refactored.chunim.endpoint.Util.EndpointUtil;
import refactored.chunim.endpoint.repository.CarRepository;
import refactored.chunim.model.Car;

@RestController
@RequestMapping("refactored.chunim/endpoint")
public class GarageEndpoint {


    private final CarRepository carRepository;
    private final EndpointUtil endpointUtil;


    @Autowired
    public GarageEndpoint(CarRepository carRepository, EndpointUtil endpointUtil) {
        this.carRepository = carRepository;

        this.endpointUtil = endpointUtil;
    }


    @ApiOperation(value = "Return a Car based on its id", response = Car.class)
    @GetMapping(path = "{id}")
    public ResponseEntity<?> getCarById(@PathVariable Integer id) {

        Car car = carRepository.findById(id).get();

        if (car == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(car, HttpStatus.OK);

    }

    @ApiOperation(value = "Delete a Car based on its id")
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Car car) {
        carRepository.delete(car);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}

//    @ApiOperation(value = "Return a CarList", response = Car.class)
//    @GetMapping(path = "list")
//    public ResponseEntity<?> ListCars(@PathVariable String name) {
//
//        Car car = carRepository.findByName(name);
//
//        if (car == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(car, HttpStatus.OK);
//

