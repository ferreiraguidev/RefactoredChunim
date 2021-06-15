package refactored.chunim.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


@Entity
public class Car {

    // what we have in database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column(nullable = false)

    private String type;

    @Column(nullable = false)

    private String brand;

    @Column(nullable = false)

    private String model;

    @Column(nullable = false)

    private String year;

    @Column(nullable = false)
    private String price;

    private String description;

    private String imagespath;

}

