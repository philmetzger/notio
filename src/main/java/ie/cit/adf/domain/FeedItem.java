package ie.cit.adf.domain;

import java.util.UUID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "feeditem")
public class FeedItem {
	
	private String id;
	private String text;
	private String category;
	private String author;

	public FeedItem() {
		id = UUID.randomUUID().toString();
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}