package com.batherphilippa.springboot.webapptodolist.todo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Todo {
	@Id
	@GeneratedValue
	private long id;
	private String username;
	@Size(min=10, message="Enter at least 10 characters")
	private String description;
	@Column(name="TARGET_DATE")
	private LocalDate target;
	private boolean done;
	
	public Todo() {}
	
	public Todo(long id, String username, String description, LocalDate target, boolean done) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.target = target;
		this.done = done;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTarget() {
		return target;
	}

	public void setTarget(LocalDate targetDate) {
		this.target = targetDate;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean isDone) {
		this.done = isDone;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
				+ target + ", isDone=" + done + "]";
	}

}
