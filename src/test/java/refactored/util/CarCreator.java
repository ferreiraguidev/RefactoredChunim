package refactored.util;


import refactored.chunim.model.Car;

public class CarCreator {

    public static Car createCarToBeSaved() {
        return Car.builder()
                .name("testingName")
                .type("testingType")
                .brand("testingBrand")
                .model("testingModel")
                .year("testingYear")
                .price("testingPrice")
                .description("testingDescription")
                .imagespath("testingImagesPath")
                .build();
    }

    public static Car createValidCar() {
        return Car.builder()
                .name("testingName")
                .type("testingType")
                .brand("testingBrand")
                .model("testingModel")
                .year("testingYear")
                .price("testingPrice")
                .description("testingDescription")
                .imagespath("testingImagesPath")
                .build();

    }

    public static Car updateCarWithValidId() {
        return Car.builder()
                .name("testingName")
                .type("testingType")
                .brand("testingBrand")
                .model("testingModel")
                .year("testingYear")
                .price("testingPrice")
                .description("testingDescription")
                .imagespath("testingImagesPath")
                .build();

    }
}
