package refactored.chunim.endpoint.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactored.chunim.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    // spring bean

    List<Car> findAll();

//
    List<Car> findByName(String name);

    // 10 min 54 sec lesson 22.....
}
