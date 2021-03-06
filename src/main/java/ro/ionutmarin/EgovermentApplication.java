package ro.ionutmarin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
public class EgovermentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgovermentApplication.class, args);
	}
}
