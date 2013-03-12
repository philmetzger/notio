package ie.cit.adf.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Todos {
	public List<Todo> todos = new ArrayList<Todo>();

	public Todos() {
	}

	public Todos(List<Todo> todos) {
		super();
		this.todos = todos;
	}
}
