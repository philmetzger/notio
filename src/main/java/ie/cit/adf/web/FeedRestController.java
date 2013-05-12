package ie.cit.adf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ie.cit.adf.domain.FeedItem;
import ie.cit.adf.domain.FeedItems;
import ie.cit.adf.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping("api")
public class FeedRestController {

	@Autowired
	private FeedService feedService;

	// curl -X GET -i http://localhost:8080/todo-app/api/feed
	@RequestMapping(value = "feed", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public FeedItems todos() {
		return new FeedItems(feedService.getAllFeedItems());
	}
	
	// curl -X GET -i http://localhost:8080/todo-app/api/authorfeeditems/{author}
	@RequestMapping(value = "authorfeeditems/{author}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public FeedItems authorFeedItems(@PathVariable String author) {
		return new FeedItems(feedService.getAuthorFeedItems(author));
	}

	// curl -X GET -i http://localhost:8080/todo-app/api/feeditem/{id}
	@RequestMapping(value = "feeditem/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public FeedItem feedItem(@PathVariable String id) {
		FeedItem feedItem = feedService.get(id);
		if (feedItem == null) {
			throw new NotFoundException();
		}
		
		return feedItem;
	}

	@RequestMapping(value = "feeditem", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestParam String text, @RequestParam String category, HttpServletRequest req, HttpServletResponse resp) {
		FeedItem feedItem = feedService.createFeedItem(text, category);
	
		StringBuffer url = req.getRequestURL().append("/{id}");
		UriTemplate uriTemplate = new UriTemplate(url.toString());
		resp.addHeader("location", uriTemplate.expand(feedItem.getId()).toASCIIString());
	}

	// curl -X DELETE -i http://localhost:8080/todo-app/api/feeditem/{id}
	@RequestMapping(value = "feeditem/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		feedService.delete(id);
	}

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}