package snmaddula.pochub.ios.config.ignite;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.Setter;

@Setter
@Configuration
@ConfigurationProperties("ignite.datasource")
public class IgniteConfig {
	
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
	
	@Bean
	public JdbcTemplate igniteTemplate(DataSource igniteDS) {
		return new JdbcTemplate(igniteDS);
	}
}
