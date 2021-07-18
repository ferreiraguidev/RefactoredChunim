package refactored.chunim.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class GarageAdmin extends AbstractEntity {


    @NotBlank(message = "username can not be empty")
    private String username;

    @NotBlank(message = "password can not be empty")
    private String password;

    @NotBlank(message = "authority must be defined")
    private String authorities; // Role Admin


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Arrays.stream(authorities.split(" , "))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());


}
