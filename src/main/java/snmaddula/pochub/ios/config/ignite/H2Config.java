package snmaddula.pochub.ios.config.ignite;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;

@Setter
@Configuration
@ConfigurationProperties("h2.datasource")
public class H2Config {
	
	private String url;
	private String username;
	private String password;
	private String driverClassName;

	@Bean
	public DataSource igniteDS() {
		return DataSourceBuilder.create()
				.url(url)
				.username(username)
				.password(password)
				.driverClassName(driverClassName)
				.build();
	}
}
