package ie.cit.adf.web;

import ie.cit.adf.domain.Todo;
import ie.cit.adf.services.TodoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class TodoRestController {
	@Autowired
	private TodoService todoService;

	@ResponseBody
	@RequestMapping("todo")
	// http://server:port/todo/api/todo
	public List<Todo> todos() {
		return todoService.getAllTodos();
	}
}
