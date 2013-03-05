package ie.cit.adf.services;

import ie.cit.adf.domain.Todo;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTodoServiceImpl implements TodoService {

	private List<Todo> todos = new ArrayList<Todo>();

	public InMemoryTodoServiceImpl() {
		Todo todo = new Todo();
		todo.setText("Remember the milk!");
		todos.add(todo);
	}

	public List<Todo> getAllTodos() {
		return todos;
	}

	public void createNewTodo(String text) {
		Todo todo = new Todo();
		todo.setText(text);
		todos.add(todo);
	}

	public void close(int todoId) {
		todos.get(todoId - 1).setDone(true);
	}

	public void open(int todoId) {
		todos.get(todoId - 1).setDone(false);
	}

}
