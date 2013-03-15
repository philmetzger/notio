package ie.cit.adf.domain.dao;

import ie.cit.adf.domain.Todo;

import java.util.List;

public interface TodoRepository {

	Todo findById(String id);

	List<Todo> getAll();

	void add(Todo todo);

	void delete(String todoId);

	void update(Todo todo);

}
