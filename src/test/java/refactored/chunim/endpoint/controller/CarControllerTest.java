package refactored.chunim.endpoint.controller;


import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import refactored.chunim.RefactoredChunimApplication;

import refactored.chunim.endpoint.service.CarService;
import refactored.chunim.model.Car;
import refactored.chunim.request.CarPostRequestBody;
import refactored.chunim.request.CarPutRequestBody;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefactoredChunimApplication.class)
class CarControllerTest {


    @Autowired
    private CarController carController;

    @Autowired
    private CarService carService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private CarPostRequestBody carMocked() {
        CarPostRequestBody carPostRequestBody = new CarPostRequestBody();
        carPostRequestBody.setId(20);
        carPostRequestBody.setName("CARRO X");
        carPostRequestBody.setType("TESTE");
        carPostRequestBody.setBrand("TESTE");
        carPostRequestBody.setModel("TESTE");
        carPostRequestBody.setYear("TESTE");
        carPostRequestBody.setPrice("TESTE");
        carPostRequestBody.setDescription("TESTE");
        carPostRequestBody.setImagespath("TESTE");
        return carPostRequestBody;
    }



    @Test
    @DisplayName("Returns a List When Successfull")
    void list_ReturnListOfCars() {
        CarPostRequestBody car2 = carMocked();
        car2.setId(25);
        car2.setName("CARRO Y");

        ResponseEntity<Car> savedCar = carController.save(carMocked());
        ResponseEntity<Car> savedCar2 = carController.save(car2);

        carController.listAll();

        assertThat(carController.listAll().getBody().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Find By id when Successfull")
    void findById_ReturnCarById_WhenSuccessfull() {
        ResponseEntity<Car> savedCar = carController.save(carMocked());
        ResponseEntity<Car> findById = carController.findById(savedCar.getBody().getId());

        assertThat(findById.getStatusCodeValue()).isEqualTo(200);
        assertThat(findById.getBody().getName()).isEqualTo("CARRO X");
    }

    @Test
    @DisplayName("Saves when Successfull")
    void save_ReturnCar_WhenSuccessfull() {
        ResponseEntity<Car> savedCar = carController.save(carMocked());

        assertThat(savedCar.getBody()).isNotNull();
        assertThat(savedCar.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    @DisplayName("Delete car")
    void delete() {
        ResponseEntity<Car> savedCar = carController.save(carMocked());
        ResponseEntity<Car> deleteCar = carController.delete(savedCar.getBody().getId());

        assertThat(deleteCar.getStatusCodeValue()).isEqualTo(204);
        assertThat(deleteCar.getBody()).isNull();
    }

    @Test
    void update() {
        ResponseEntity<Car> savedCar = carController.save(carMocked());
        ResponseEntity<Car> findCarByID = carController.findById(savedCar.getBody().getId());
        assertThat(findCarByID.getBody().getName()).isEqualTo("CARRO X");

        CarPutRequestBody carPutRequestBody = new CarPutRequestBody();
        carPutRequestBody.setId(findCarByID.getBody().getId());
        carPutRequestBody.setName("CARRO Y");
        carPutRequestBody.setType("X");
        carPutRequestBody.setBrand("X");
        carPutRequestBody.setModel("X");
        carPutRequestBody.setYear("X");
        carPutRequestBody.setPrice("X");
        carPutRequestBody.setDescription("X");
        carPutRequestBody.setImagespath("X");

        carController.update(carPutRequestBody);

        ResponseEntity<Car> findCarByID2 = carController.findById(savedCar.getBody().getId());
        assertThat(findCarByID2.getBody().getName()).isEqualTo("CARRO Y");
    }


}