package snmaddula.pochub.ios;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties
public class IgniteOracleSpringApp {

	public static void main(String[] args) {
		SpringApplication.run(IgniteOracleSpringApp.class, args);
	}

	@Bean
	CommandLineRunner cli(DataSource igniteDS) {
		return (args) -> {
			System.out.println(igniteDS.getConnection());
		};
	}
}
