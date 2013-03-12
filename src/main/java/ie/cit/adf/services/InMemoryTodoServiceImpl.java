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

	public String createNewTodo(String text) {
		Todo todo = new Todo();
		todo.setText(text);
		todos.add(todo);
		return todo.getId();
	}

	public void close(String todoId) {
		for (Todo todo : todos) {
			if (todo.getId().equals(todoId)) {
				todo.setDone(true);
			}
		}
	}

	public void open(String todoId) {
		for (Todo todo : todos) {
			if (todo.getId().equals(todoId)) {
				todo.setDone(false);
			}
		}
	}

	public Todo get(String todoId) {
		for (Todo todo : todos) {
			if (todo.getId().equals(todoId)) {
				return todo;
			}
		}
		return null;
	}

	@Override
	public void delete(String todoId) {
		for (Todo todo : todos) {
			if (todo.getId().equals(todoId)) {
				todos.remove(todo);
				break;
			}
		}
	}
}
