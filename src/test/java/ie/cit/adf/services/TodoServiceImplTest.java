package ie.cit.adf.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import ie.cit.adf.domain.Todo;
import ie.cit.adf.domain.dao.TodoRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TodoServiceImplTest {

	private TodoServiceImpl tested;
	private TodoRepository todoRepository;

	@Before
	public void setup() {
		todoRepository = Mockito.mock(TodoRepository.class);
		tested = new TodoServiceImpl(todoRepository);
	}

	@Test
	public void testCreateTodo() {
		Todo createNewTodo = tested.createNewTodo("Todo 1");
		Mockito.verify(todoRepository).add(createNewTodo);
		assertThat(createNewTodo.getText(), equalTo("Todo 1"));
	}

}
