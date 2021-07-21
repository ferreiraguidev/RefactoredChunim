package refactored.chunim.model.request;


import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class CarPostRequestBody {

    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    @NotBlank
    private String year;
    @NotBlank
    private String price;

    private String description;

    private String imagespath;

}

