package refactored.chunim.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactored.chunim.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    // spring bean


    Optional<List<Car>> findByName(String name);




}
