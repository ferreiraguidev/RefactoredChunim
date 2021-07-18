package refactored.chunim.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
public class ApplicationUser extends AbstractEntity {

    @NotBlank(message = "username can not be empty")
    private String username;

    @NotBlank(message = "password can not be empty")
    private String password;

//    @NotBlank(message = "authorities can not be empty")
//    private String authorities; // Role Admin

    @OneToOne
    private GarageAdmin garageAdmin;

    public ApplicationUser(ApplicationUser applicationUser) {
        this.username = applicationUser.username;
        this.password = applicationUser.password;
        this.garageAdmin = applicationUser.garageAdmin;
    }

    public ApplicationUser() {

    }
}


