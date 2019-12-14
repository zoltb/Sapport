package App;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringStartPoint implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringStartPoint.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        try {

        System.out.println(888);
    } catch (Exception e){
        System.out.println(e);
    }

}
}