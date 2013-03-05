package ie.cit.adf.web;

import ie.cit.adf.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
	@Autowired
	private TodoService todoService;

	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("todos", todoService.getAllTodos());
		return "todos.jsp";
	}
	
	@RequestMapping("create")
	public String create(@RequestParam String text, Model model) {
		todoService.createNewTodo(text);
		model.addAttribute("todos", todoService.getAllTodos());
		return "todos.jsp";
	}

	@RequestMapping("close")
	public String close(@RequestParam int todoId, Model model) {
		todoService.close(todoId);
		model.addAttribute("todos", todoService.getAllTodos());
		return "todos.jsp";
	}
	
	@RequestMapping("open")
	public String open(@RequestParam int todoId, Model model) {
		todoService.open(todoId);
		model.addAttribute("todos", todoService.getAllTodos());
		return "todos.jsp";
	}

}
