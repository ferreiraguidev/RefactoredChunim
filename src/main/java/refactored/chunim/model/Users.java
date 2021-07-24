package refactored.chunim.model;

import lombok.Data;

import javax.persistence.Entity;


@Entity
@Data
public class Users extends AbstractEntity {

    private String name;
    private String username;
    private String password;
    private boolean admin;

}
