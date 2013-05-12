package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.FeedItem;
import java.util.List;

public interface FeedRepository {

	FeedItem findById(String id);

	List<FeedItem> findAll();

	List<FeedItem> findAllForAccount();

	List<FeedItem> findAllByCategory(String category);

	List<FeedItem> findAllByCategoryForAccount(String category);
	
	List<FeedItem> findAllByAuthor(String author);

	void add(FeedItem feedItem);

	void delete(String feedItemId);

}
