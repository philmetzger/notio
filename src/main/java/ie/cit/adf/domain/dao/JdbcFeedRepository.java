package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.FeedItem;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured("ROLE_USER")
public class JdbcFeedRepository implements FeedRepository {

	private JdbcTemplate jdbcTemplate;
	private FeedItemsMapper feedItemsMapper = new FeedItemsMapper();

	public JdbcFeedRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public FeedItem findById(String feedItemId) {
		return jdbcTemplate.queryForObject(
			"SELECT ID, TEXT, CATEGORY, AUTHOR FROM FEEDITEMS WHERE ID=?", 
			feedItemsMapper, 
			feedItemId
		);
	}

	@Override
	public List<FeedItem> findAll() {
		return jdbcTemplate.query(
			"SELECT ID, TEXT, CATEGORY, AUTHOR FROM FEEDITEMS", 
			feedItemsMapper
		);
	}

	@Override
	public List<FeedItem> findAllForAccount() {
		return jdbcTemplate.query(
			"SELECT ID, TEXT, CATEGORY, AUTHOR FROM FEEDITEMS WHERE AUTHOR=?", 
			feedItemsMapper,
			getCurrentUser()
		);
	}
	
	@Override
	public List<FeedItem> findAllByCategory(String category) {
		return jdbcTemplate.query(
				"SELECT ID, TEXT, CATEGORY, AUTHOR FROM FEEDITEMS WHERE CATEGORY=?", 
				feedItemsMapper,
				category
			);
	}
	
	@Override
	public List<FeedItem> findAllByCategoryForAccount(String category) {
		return jdbcTemplate.query(
				"SELECT ID, TEXT, CATEGORY, AUTHOR FROM FEEDITEMS WHERE CATEGORY=? AND AUTHOR=?", 
				feedItemsMapper,
				category,
				getCurrentUser()
			);
	}
	
	@Override
	public List<FeedItem> findAllByAuthor(String author) {
		return jdbcTemplate.query(
			"SELECT ID, TEXT, CATEGORY, AUTHOR FROM FEEDITEMS WHERE AUTHOR=?", 
			feedItemsMapper,
			author
		);
	}
	
	@Override
	public void add(FeedItem feedItem) {
		jdbcTemplate.update(
			"INSERT INTO FEEDITEMS VALUES(?,?,?,?)",
			feedItem.getId(),
			feedItem.getText(),
			feedItem.getCategory(),
			getCurrentUser()
		);
	}

	@Override
	public void delete(String feedItemId) {
		jdbcTemplate.update("DELETE FROM FEEDITEMS WHERE ID=?", feedItemId);
	}
	
	/**
	 * Returns the name of the currently logged in Author.
	 * 
	 * @return String
	 */
	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

}
