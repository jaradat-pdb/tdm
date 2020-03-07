/*
 * Copyright 2020 PDB Corp.
 *
 * Proprietary Software built off of open-source software?
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pdbcorp.mgmt.todo.controller;

import java.util.Optional;

import org.pdbcorp.mgmt.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jaradat-pdb
 *
 */
@Controller
public class TodoController {
	private String modelAttrName = "todos";
	private String redirectStr = "redirect:/todos";
	private PagingAndSortingRepository<Todo, Long> todoRepository;

	@Autowired
	public TodoController(PagingAndSortingRepository<Todo, Long> todoRepository) {
		this.todoRepository = todoRepository;
	}

	@GetMapping("/todos")
	public String todos(Model model) {
		model.addAttribute(modelAttrName, todoRepository.findAll());
		return "todos";
	}

	@PostMapping("/todos/new")
	public String add(@RequestParam String todoItem, @RequestParam String todoStatus, Model model) {
		todoRepository.save(new Todo(todoItem, todoStatus));
		model.addAttribute(modelAttrName, todoRepository.findAll());
		return redirectStr;
	}

	@PostMapping("/todos/delete/{id}")
	public String deleteTodo(@PathVariable long id, Model model) {
		todoRepository.deleteById(id);
		model.addAttribute(modelAttrName, todoRepository.findAll());
		return redirectStr;
	}

	@PostMapping("/todos/update/{id}")
	public String updateTodo(@PathVariable long id, Model model) {
		Optional<Todo> value = todoRepository.findById(id);
		if(value.isPresent()) {
			Todo todo = value.get();
			if("Yes".equalsIgnoreCase(todo.getTodoStatus())) {
				todo.setTodoStatus("No");
			}
			else {
				todo.setTodoStatus("Yes");
			}
			todoRepository.save(todo);
		}
		model.addAttribute(modelAttrName, todoRepository.findAll());
		return redirectStr;
	}
}
