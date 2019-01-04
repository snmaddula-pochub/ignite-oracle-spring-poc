package snmaddula.pochub.ios.config.ignite;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

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
	public DataSource h2DS() {
		return DataSourceBuilder.create().url(url).username(username).password(password)
				.driverClassName(driverClassName).build();
	}

	/*@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}*/

	@Bean
	public JdbcTemplate h2Template(DataSource h2DS) {
		return new JdbcTemplate(h2DS);
	}
}
