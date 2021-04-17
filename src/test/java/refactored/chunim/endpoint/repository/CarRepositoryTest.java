package refactored.chunim.endpoint.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import refactored.chunim.model.Car;
import refactored.util.CarCreator;

import java.util.Optional;

@DataJpaTest
@DisplayName("Car Repository test")
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    @DisplayName("creates when succesfull")
    void save_PersistCar_WhenSuccesfull() {

        Car carToBeSaved = CarCreator.createCarToBeSaved();
        Car savedCar = this.carRepository.save(carToBeSaved);

        Assertions.assertThat(savedCar).isNotNull();
        Assertions.assertThat(savedCar.getId()).isNotNull();
        Assertions.assertThat(savedCar.getName()).isNotNull();
        Assertions.assertThat(savedCar.getType()).isNotNull();
        Assertions.assertThat(savedCar.getBrand()).isNotNull();
        Assertions.assertThat(savedCar.getModel()).isNotNull();
        Assertions.assertThat(savedCar.getYear()).isNotNull();
        Assertions.assertThat(savedCar.getPrice()).isNotNull();
        Assertions.assertThat(savedCar.getDescription()).isNotNull();
        Assertions.assertThat(savedCar.getImagespath()).isNotNull();

    }

    @Test
    @DisplayName("updates when succesfull")
    void update_PersistCar_WhenSuccesfull() {

        Car carToBeSaved = CarCreator.createCarToBeSaved();

        Car savedCar = this.carRepository.save(carToBeSaved);

        savedCar.setName("anyCarName");
        savedCar.setType("anyCarName");
        savedCar.setBrand("anyCarName");
        savedCar.setModel("anyCarName");
        savedCar.setYear("anyCarName");
        savedCar.setPrice("anyCarName");
        savedCar.setDescription("anyCarName");
        savedCar.setImagespath("anyCarName");

        Car updatedCar = this.carRepository.save(savedCar);

        Assertions.assertThat(updatedCar).isNotNull();
        Assertions.assertThat(updatedCar.getId()).isNotNull();
        Assertions.assertThat(updatedCar.getName()).isEqualTo(savedCar.getName());
        Assertions.assertThat(updatedCar.getType()).isEqualTo(savedCar.getName());
        Assertions.assertThat(updatedCar.getBrand()).isEqualTo(savedCar.getName());
        Assertions.assertThat(updatedCar.getModel()).isEqualTo(savedCar.getName());
        Assertions.assertThat(updatedCar.getPrice()).isEqualTo(savedCar.getName());
        Assertions.assertThat(updatedCar.getYear()).isEqualTo(savedCar.getName());
        Assertions.assertThat(updatedCar.getDescription()).isEqualTo(savedCar.getName());
        Assertions.assertThat(updatedCar.getImagespath()).isEqualTo(savedCar.getName());

    }

    @Test
    @DisplayName("delete when succesfull")
    void delete_PersistCar_WhenSuccesfull() {

        Car carToBeSaved = CarCreator.createCarToBeSaved();
        Car savedCar = this.carRepository.save(carToBeSaved);

        this.carRepository.delete(savedCar);

        Optional<Car> carOptional = this.carRepository.findById(savedCar.getId());

        Assertions.assertThat(carOptional).isEmpty();

    }
}