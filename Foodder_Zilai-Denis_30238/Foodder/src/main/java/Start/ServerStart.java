package Start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("Persistence")
@ComponentScan("Controller")
@SpringBootApplication
public class ServerStart {
    public static void main(String[] args) {
        SpringApplication.run(ServerStart.class, args);
    }
}
