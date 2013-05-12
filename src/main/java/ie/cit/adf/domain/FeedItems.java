package ie.cit.adf.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FeedItems {
	public List<FeedItem> feedItems = new ArrayList<FeedItem>();

	public FeedItems() {
		
	}

	public FeedItems(List<FeedItem> feedItems) {
		super();
		this.feedItems = feedItems;
	}

}