package refactored.chunim.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import refactored.chunim.model.ApplicationUser;

@Repository
public interface AdminRepository extends PagingAndSortingRepository<ApplicationUser, Long> {
    // spring bean
    ApplicationUser findByUsername(String username);

}
