package com.batherphilippa.springboot.webapptodolist.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

// @Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}

	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedinUsername();
		List<Todo> todos = todoService.findTodoByUsername(username);
		model.put("todos", todos);
		return "todos";
	}

	@RequestMapping(value = "add-todo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = getLoggedinUsername();
		// set default values; 1 side binding
		Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="add-todo", method = RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedinUsername();
		todoService.addTodo(username, todo.getDescription(), 
				todo.getTarget());
		return "redirect:list-todos";
	}

	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam String id) {
		todoService.deleteTodoById(id);
		return "redirect:list-todos";
	}

	@RequestMapping(value="update-todo", method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam String id, ModelMap model) {
		Todo todo = todoService.findTodoById(id);
		if (todo == null) {
			return "redirect:list-todos";
		} else {
			//  .addAttribute() does an additional null check before calling a .put() (cf. .put())
			model.addAttribute("todo", todo);
			return "todo";
		}
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors() ) {
			return "todo";
		}
		String username = getLoggedinUsername();
		todoService.updateTodo(todo, username);
		return "redirect:list-todos";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		return authentication.getName();
	}

}
