package ie.cit.adf.web;

import ie.cit.adf.services.AccountService;
import ie.cit.adf.services.FeedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

	@Autowired
	private AccountService accountService;

	@RequestMapping("createAccount")
	public String index(@RequestParam String username, @RequestParam String password, Model model) {
		accountService.createAccount(username, password);
		model.addAttribute("signedUp", "yes");
		return "index.jsp";
	}
	
	/*@RequestMapping("create")
	public String create(@RequestParam(required = false) String text, 
		 				 @RequestParam(required = false) String category,
						 @RequestParam(required = false) String author,  
						 Model model) {
		if (text != "" && author != "" && category != "") {
			feedService.createFeedItem(text, category, author);
		}
		
		model.addAttribute("feedItems", feedService.getAllFeedItems());
		return "feed.jsp";
	}*/

}