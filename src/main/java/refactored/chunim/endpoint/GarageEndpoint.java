package refactored.chunim.endpoint;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<?> getCarById(@PathVariable Integer id ){

        Car car = carRepository.findById(id).get();

        if (car == null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(car , HttpStatus.OK);


    }
}
