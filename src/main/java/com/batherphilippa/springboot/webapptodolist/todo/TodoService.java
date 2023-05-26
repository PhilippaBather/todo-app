package com.batherphilippa.springboot.webapptodolist.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todoList = new ArrayList<>();

	private static long todosCount = 0;

	static {
		todoList.add(new Todo(++todosCount, "bart", "Learn Java", LocalDate.now().plusYears(1), false));
		todoList.add(new Todo(++todosCount, "bart", "Learn Spring Framework", LocalDate.now().plusYears(2), false));
		todoList.add(new Todo(++todosCount, "bart", "Learn ReactJS", LocalDate.now().plusYears(1), false));
	}

	public List<Todo> findTodoByUsername(String username) {
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todoList.stream().filter(predicate).toList();
	}

	public void addTodo(String username, String description, LocalDate targetDate) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, false);
		todoList.add(todo);
	}

	public void deleteTodoById(String id) {
		long idL = Long.parseLong(id);
		Predicate<? super Todo> predicate = todo -> todo.getId() == idL;
		todoList.removeIf(predicate);
	}

	public Todo findTodoById(String id) {
		long idL = Long.parseLong(id);
		Todo todo = getTodo(idL);
		return todo;
	}

	public void updateTodo(@Valid Todo updatedTodo, String username) {
		// course deleted object and then added it
		// update incomplete fields
		updatedTodo.setUsername(username);
		Todo todo = getTodo(updatedTodo.getId());
		int index = todoList.indexOf(todo);
		todoList.set(index, updatedTodo);
	}
	
	private Todo getTodo(long id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		return todoList.stream().filter(predicate).findFirst().get();
	}

}
