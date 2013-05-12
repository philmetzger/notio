package ie.cit.adf.web;

import ie.cit.adf.services.AccountService;
import ie.cit.adf.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedController {

	@Autowired
	private FeedService feedService;

	@Autowired
	private AccountService accountService;

	@RequestMapping("feed")
	public String index(Model model) {
		model.addAttribute("feedItems", feedService.getAllFeedItemsForAccount());
		return "feed.jsp";
	}
	
	@RequestMapping("create")
	public String create(@RequestParam(required = false) String text, 
		 				 @RequestParam(required = false) String category,
						 Model model) {
		if (text != "" && category != "") {
			feedService.createFeedItem(text, category);
		}
		
		model.addAttribute("feedItems", feedService.getAllFeedItemsForAccount());
		return "feed.jsp";
	}
	
	@RequestMapping("search")
	public String create(@RequestParam(required = false) String category, Model model) {
		if (category == "") {
			model.addAttribute("feedItems", feedService.getAllFeedItemsForAccount());
			return "feed.jsp";
		}
		
		model.addAttribute("feedItems", feedService.getAllFeedItemsByCategoryForAccount(category));
		return "feed.jsp";
	}
	
	@RequestMapping("delete")
	public String close(@RequestParam String feedItemId, Model model) {
		feedService.delete(feedItemId);
		model.addAttribute("feedItems", feedService.getAllFeedItemsForAccount());
		return "feed.jsp";
	}

}