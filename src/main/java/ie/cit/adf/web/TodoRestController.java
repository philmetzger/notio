package ie.cit.adf.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ie.cit.adf.domain.Todo;
import ie.cit.adf.domain.Todos;
import ie.cit.adf.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
@RequestMapping("api")
public class TodoRestController {
	@Autowired
	private TodoService todoService;

	// curl -X GET -i http://localhost:8080/todo-app/api/todo
	@RequestMapping(value = "todo", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todos todos() {
		return new Todos(todoService.getAllTodos());
	}

	// curl -X GET -i http://localhost:8080/todo-app/api/todo/{id}
	@RequestMapping(value = "todo/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Todo todo(@PathVariable String id) {
		Todo todo = todoService.get(id);
		if (todo == null)
			throw new NotFoundException();
		return todo;
	}

	// curl -X POST -i http://localhost:8080/todo-app/api/todo?text=TodoItem1
	@RequestMapping(value = "todo", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void create(@RequestParam String text, HttpServletRequest req,
			HttpServletResponse resp) {
		Todo todo= todoService.createNewTodo(text);
		StringBuffer url = req.getRequestURL().append("/{id}");
		UriTemplate uriTemplate = new UriTemplate(url.toString());
		resp.addHeader("location", uriTemplate.expand(todo.getId()).toASCIIString());
	}

	// curl -X DELETE -i http://localhost:8080/todo-app/api/todo/{id}
	@RequestMapping(value = "todo/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		todoService.delete(id);
	}

	// curl -X PUT -i http://localhost:8080/todo-app/api/todo/{id} -d
	// '{"text":"TODO Item Text","done":true}'
	@RequestMapping(value = "todo/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable String id, @RequestBody Todo todo) {
		Todo existing = todoService.get(id);
		if (existing == null)
			throw new NotFoundException();
		existing.setText(todo.getText());
		existing.setDone(todo.isDone());
	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
