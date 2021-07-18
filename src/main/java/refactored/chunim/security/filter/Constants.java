package refactored.chunim.security.filter;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Constants {

    public static final String SECRET = "sacre";
    public static final String TOKEN_PREFIX = "sacreDDD";
    public static final String HEADER = "auth";
    public static final Long EXPIRATION_TIME = 86400000L; // ONE DAY* in miliseconds


    public static void main(String[] args) {


        System.out.println("whatever testing");
        System.out.println(new BCryptPasswordEncoder().encode("MyJwtPassword"));


    }
}
