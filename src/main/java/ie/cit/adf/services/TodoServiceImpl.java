package ie.cit.adf.services;

import ie.cit.adf.domain.Todo;
import ie.cit.adf.domain.dao.TodoRepository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TodoServiceImpl implements TodoService {
	private TodoRepository repo;

	public TodoServiceImpl(TodoRepository repo) {
		this.repo = repo;
	}

	public List<Todo> getAllTodos() {
		return repo.getAll();
	}

	public Todo createNewTodo(String text) {
		Todo todo = new Todo();
		todo.setText(text);
		repo.add(todo);
		return todo;
	}

	public void close(String todoId) {
		Todo todo = repo.findById(todoId);
		if (todo != null) {
			todo.setDone(true);
			repo.update(todo);
		}
	}

	public void open(String todoId) {
		Todo todo = repo.findById(todoId);
		if (todo != null) {
			todo.setDone(false);
			repo.update(todo);
		}
	}

	public Todo get(String todoId) {
		return repo.findById(todoId);
	}

	@Override
	public void delete(String todoId) {
		repo.delete(todoId);
	}
}
