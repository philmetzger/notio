package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Todo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTodoRepository implements TodoRepository {
	private Map<String, Todo> data = new HashMap<String, Todo>();

	@Override
	public Todo findById(String id) {
		return data.get(id);
	}

	@Override
	public List<Todo> getAll() {
		return new ArrayList<Todo>(data.values());
	}

	@Override
	public void add(Todo todo) {
		data.put(todo.getId(), todo);
	}

	@Override
	public void delete(String todoId) {
		data.remove(todoId);
	}

	@Override
	public void update(Todo todo) {
		data.put(todo.getId(), todo);
	}

}
