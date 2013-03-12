package ie.cit.adf.services;

import ie.cit.adf.domain.Todo;

import java.util.List;

public interface TodoService {

	List<Todo> getAllTodos();

	String createNewTodo(String text);

	void close(String todoId);

	void open(String todoId);

	Todo get(String todoId);

	void delete(String todoId);

}
