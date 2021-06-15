package refactored.chunim.endpoint.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactored.chunim.model.GarageAdmin;

@Repository
public interface AdminRepository extends JpaRepository<GarageAdmin, Integer> {
    // spring bean

    GarageAdmin findByUsername(String userName);
}
