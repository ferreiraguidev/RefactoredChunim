package refactored.chunim.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Users extends AbstractEntity {

    private String name;
    private String username;
    private String password;
    private boolean admin;

}
