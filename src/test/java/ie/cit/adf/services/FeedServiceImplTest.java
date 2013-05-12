package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import ie.cit.adf.domain.FeedItem;
import ie.cit.adf.domain.FeedItems;
import ie.cit.adf.domain.dao.FeedRepository;
import org.apache.taglibs.standard.lang.jstl.NotEqualsOperator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import static org.mockito.Mockito.*;

public class FeedServiceImplTest {

	private FeedServiceImpl feedServiceTested;
	private FeedRepository feedRepository;

	@Before
	public void setup() {
		feedRepository = Mockito.mock(FeedRepository.class);
		feedServiceTested = new FeedServiceImpl(feedRepository);
	}

	@Test
	public void testCreateFeedItem() {
		FeedItem feedItem = feedServiceTested.createFeedItem("Text Test", "Category Test");
		Mockito.verify(feedRepository).add(feedItem);
		assertThat(feedItem.getText(), equalTo("Text Test"));
	}

	@Test
	public void testDeleteFeedItem() {
		FeedItem feedItem = feedServiceTested.createFeedItem("Text Test", "Category Test");
		Mockito.verify(feedRepository).add(feedItem);

		String deletedFeedItemId = feedItem.getId();
		
		assertThat(feedItem, not(equalTo(null)));
		
		feedServiceTested.delete(feedItem.getId());
		
		FeedItem deletedFeedItem = feedServiceTested.get(deletedFeedItemId);

		assertThat(deletedFeedItem, equalTo(null));
	}
	
	@Test
	public void testGetAllFeedItems() {
		FeedItem feedItem = feedServiceTested.createFeedItem("Text Test", "Category Test");
		Mockito.verify(feedRepository).add(feedItem);

		List<FeedItem> feedItemsList = new ArrayList<FeedItem>();
		feedItemsList.add(feedItem);
		when(feedRepository.findAll()).thenReturn(feedItemsList);
		
		List<FeedItem> feedItems = feedServiceTested.getAllFeedItems();
		Mockito.verify(feedRepository).findAll();
		assertThat(feedItems.size(), equalTo(1));
	}
	
	@Test
	public void testSearchFeedItemsByCategory() {
		FeedItem feedItem = feedServiceTested.createFeedItem("Text Test", "Category Test");
		Mockito.verify(feedRepository).add(feedItem);

		List<FeedItem> feedItemsList = new ArrayList<FeedItem>();
		feedItemsList.add(feedItem);
		when(feedRepository.findAllByCategory("Category Test")).thenReturn(feedItemsList);
		
		List<FeedItem> feedItems = feedServiceTested.getAllFeedItemsByCategory("Category Test");
		Mockito.verify(feedRepository).findAllByCategory("Category Test");
		assertThat(feedItems.size(), equalTo(1));
	}

}