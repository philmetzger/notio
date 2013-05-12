package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.FeedItem;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

class FeedItemsMapper implements RowMapper<FeedItem> {
	@Override
	public FeedItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		FeedItem feedItem = new FeedItem();
		feedItem.setId(rs.getString("ID"));
		feedItem.setText(rs.getString("TEXT"));
		feedItem.setCategory(rs.getString("CATEGORY"));
		feedItem.setAuthor(rs.getString("AUTHOR"));
		return feedItem;
	}
}