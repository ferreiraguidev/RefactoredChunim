package refactored.chunim.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import refactored.chunim.repository.CarRepository;
import refactored.chunim.handler.exceptions.BadRequestException;
import refactored.chunim.model.Car;
import refactored.chunim.model.request.CarPostRequestBody;
import refactored.chunim.model.request.CarPutRequestBody;

import java.util.List;


@Service
@RequiredArgsConstructor // dependency injections
public class CarService {
    // business logic

    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Integer id) {

        return carRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Could not find the correspondent ID"));
    }

    public List<Car> findByName(String name) {

        return carRepository.findByName(name)
                .orElseThrow(()->new BadRequestException("Could not find a correspondent car"));

    }

    public Car save(CarPostRequestBody carPostRequestBody) {

        Car car = Car.builder()
                .name(carPostRequestBody.getName())
                .type(carPostRequestBody.getType())
                .brand(carPostRequestBody.getBrand())
                .model(carPostRequestBody.getModel())
                .year(carPostRequestBody.getYear())
                .price(carPostRequestBody.getPrice())
                .description(carPostRequestBody.getDescription())
                .imagespath(carPostRequestBody.getImagespath())
                .build();

        return carRepository.save(car);

    }

    public void delete(Integer id) {
        carRepository.deleteById(id);
    }

    public void update(CarPutRequestBody carPutRequestBody) {

        Car savedCar = findById(carPutRequestBody.getId());

        Car car = Car.builder()
                .id(savedCar.getId())
                .name(carPutRequestBody.getName())
                .type(carPutRequestBody.getType())
                .brand(carPutRequestBody.getBrand())
                .model(carPutRequestBody.getModel())
                .year(carPutRequestBody.getYear())
                .price(carPutRequestBody.getPrice())
                .description(carPutRequestBody.getDescription())
                .imagespath(carPutRequestBody.getImagespath())
                .build();

        carRepository.save(car);

    }

}
