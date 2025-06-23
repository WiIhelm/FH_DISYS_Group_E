//@file: java
package at.fhtechnikum.echo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "at.fhtechnikum.echo2")
public class CPSApplication {
    public static void main(String[] args) {
        SpringApplication.run(CPSApplication.class, args);
    }
}