package ie.cit.adf.services;

import ie.cit.adf.domain.FeedItem;
import java.util.List;

public interface FeedService {

	List<FeedItem> getAllFeedItems();

	List<FeedItem> getAllFeedItemsForAccount();

	List<FeedItem> getAllFeedItemsByCategory(String category);

	List<FeedItem> getAllFeedItemsByCategoryForAccount(String category);

	FeedItem get(String feedItemId);
	
	List<FeedItem> getAuthorFeedItems(String author);

	FeedItem createFeedItem(String text, String category);

	void delete(String feedItemId);

}