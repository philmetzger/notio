package ie.cit.adf.services;

import ie.cit.adf.domain.FeedItem;
import ie.cit.adf.domain.dao.FeedRepository;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class FeedServiceImpl implements FeedService {

	private FeedRepository repo;

	public FeedServiceImpl(FeedRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public FeedItem get(String feedItemId) {
		return repo.findById(feedItemId);
	}
	
	@Override
	public List<FeedItem> getAllFeedItems() {
		return repo.findAll();
	}
	
	@Override
	public List<FeedItem> getAllFeedItemsForAccount() {
		return repo.findAllForAccount();
	}
	
	@Override
	public List<FeedItem> getAllFeedItemsByCategory(String category) {
		return repo.findAllByCategory(category);
	}
	
	@Override
	public List<FeedItem> getAllFeedItemsByCategoryForAccount(String category) {
		return repo.findAllByCategoryForAccount(category);
	}
	
	@Override
	public List<FeedItem> getAuthorFeedItems(String author) {
		return repo.findAllByAuthor(author);
	}
	
	@Override
	public FeedItem createFeedItem(String text, String category) {
		FeedItem feedItem = new FeedItem();
		feedItem.setText(text);
		feedItem.setCategory(category);
		feedItem.setAuthor(getCurrentUser());

		repo.add(feedItem);

		return feedItem;
	}

	@Override
	public void delete(String feedItemId) {
		repo.delete(feedItemId);
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