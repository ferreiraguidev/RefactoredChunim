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

    @OneToOne
    private ApplicationUser applicationUser;


    @OneToOne
    private GarageAdmin garageAdmin;

    public ApplicationUser(ApplicationUser applicationUser) {
        this.username = applicationUser.username;
        this.password = applicationUser.password;
        this.applicationUser = applicationUser.applicationUser;
        this.garageAdmin = applicationUser.garageAdmin;
    }

    public ApplicationUser() {
    }


    //    public ApplicationUser(ApplicationUser applicationUser) {
//        this.username = applicationUser.username;
//        this.password = applicationUser.password;
//        this.admin = applicationUser.admin;
//        this.garageAdmin = applicationUser.garageAdmin;
//    }
//
//    public ApplicationUser() {
//    }

}


