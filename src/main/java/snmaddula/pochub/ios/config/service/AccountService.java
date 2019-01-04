package snmaddula.pochub.ios.config.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import snmaddula.pochub.ios.domain.Account;

@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class AccountService {

	private static final String FIND_BY_ID = "select * from account where id = ?";

	private JdbcTemplate h2Template;
	private JdbcTemplate igniteTemplate;

	public Account findById(Integer id) {
		Account account = null;
		return (account = findById(igniteTemplate, id)) != null ? account : findById(h2Template, id);
	}

	private Account findById(JdbcTemplate template, Integer id) {
		try {
			return (Account) template.queryForObject(FIND_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper(Account.class));
		} catch (EmptyResultDataAccessException e) {
			log.warn("No records found");
			return null;
		}
	}

	private static final String CREATE_ACCOUNT = "insert into account (id, name, email) values (?, ?, ?)";

	@Transactional
	public boolean create(Account account) {
		if(create(igniteTemplate, account) > 0 && create(h2Template, account) > 0) {
			log.info("Created a new record and synced!");
			return true;
		}
		return false;
	}

	private int create(JdbcTemplate template, Account a) {
		try {
			return template.update(CREATE_ACCOUNT, a.getId(), a.getName(), a.getEmail());
		} catch (Exception e) {
			log.error("Create failed!", e);
			return 0;
		}
	}
	
	
}
