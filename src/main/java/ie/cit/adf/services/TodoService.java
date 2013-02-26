package ie.cit.adf.services;

import ie.cit.adf.domain.Todo;

import java.util.List;

public interface TodoService {

	List<Todo> getAllTodos();

	void createNewTodo(String text);

}
