package ie.cit.adf.domain.dao;

import java.util.UUID;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcAccountRepository implements AccountRepository {

	private JdbcTemplate jdbcTemplate;

	public JdbcAccountRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void addAccount(String username, String password) {
		String id = UUID.randomUUID().toString();
		jdbcTemplate.update(
			"INSERT INTO ACCOUNTS VALUES(?,?,?)",
			id,
			username,
			password
		);
		jdbcTemplate.update(
			"INSERT INTO ACCOUNT_ROLES VALUES(?,?)",
			id,
			"ROLE_USER"
		);
	}

}
