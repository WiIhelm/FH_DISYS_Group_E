//@file: java
package at.fhtechnikum.echo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "at.fhtechnikum.echo2")
public class EchoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoServiceApplication.class, args);
    }
}