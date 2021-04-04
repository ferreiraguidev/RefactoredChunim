package refactored.chunim.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import refactored.chunim.model.Car;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Integer> {
    // spring bean

    List<Car> findAll();
}
