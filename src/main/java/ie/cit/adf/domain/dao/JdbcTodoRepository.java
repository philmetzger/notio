package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured("ROLE_USER")
public class JdbcTodoRepository implements TodoRepository {

	private JdbcTemplate jdbcTemplate;
	private TodoMapper todoMapper = new TodoMapper();

	public JdbcTodoRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Todo findById(String id) {
		return jdbcTemplate.queryForObject(
				"SELECT ID, TEXT, DONE FROM TODO WHERE ID=?", todoMapper, id);
	}

	@Override
	public List<Todo> getAll() {
		return jdbcTemplate.query(
				"SELECT ID, TEXT, DONE FROM TODO WHERE OWNER=?", todoMapper,
				getCurrentUser());
	}

	@Override
	public void add(Todo todo) {
		jdbcTemplate.update("INSERT INTO TODO VALUES(?,?,?,?)", todo.getId(),
				todo.getText(), getCurrentUser(), todo.isDone());
	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	@Override
	public void delete(String todoId) {
		jdbcTemplate.update("DELETE FROM TODO WHERE ID=?", todoId);
	}

	@Override
	public void update(Todo todo) {
		jdbcTemplate.update("UPDATE TODO SET TEXT=?, DONE=? WHERE ID=?",
				todo.getText(), todo.isDone(), todo.getId());
	}
}

class TodoMapper implements RowMapper<Todo> {
	@Override
	public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Todo todo = new Todo();
		todo.setId(rs.getString("ID"));
		todo.setText(rs.getString("TEXT"));
		todo.setDone(rs.getBoolean("DONE"));
		return todo;
	}
}
