package refactored.chunim.endpoint.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import refactored.chunim.model.GarageAdmin;



public interface AdminRepository extends JpaRepository<GarageAdmin, Integer> {
    // spring bean

    GarageAdmin findByUsername(String username);
}
