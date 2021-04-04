package refactored.chunim.request;


import lombok.Data;


@Data
public class CarPostRequestBody {

    private Integer id;
    private String name;
    private String type;
    private String brand;
    private String model;
    private String year;
    private String price;
    private String description;
    private String imagespath;

}

