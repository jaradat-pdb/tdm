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
package org.pdbcorp.mgmt.todo.boot;

import java.util.Arrays;
import java.util.Collection;

import org.pdbcorp.mgmt.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

/**
 * @author jaradat-pdb
 *
 */
@Component
public class TodoManagerBootstrap implements CommandLineRunner {
	private PagingAndSortingRepository<Todo, Long> todoRepository;

	@Autowired
	TodoManagerBootstrap(@Qualifier("todoRepository") PagingAndSortingRepository<Todo, Long> todoRepository) {
		this.todoRepository = todoRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Collection<Todo> todos = Arrays.asList(
				new Todo("Complete part 2 of this tutorial", "No"),
				new Todo("Fix ycamp site for mobile and xs screens", "No"),
				new Todo("Finish Web Developer Bootcamp", "Yes"),
				new Todo("Finish reading Ayyuha alWalid", "Yes"));
		// todos.forEach(todoRepository::save); ==> https://stackoverflow.com/questions/27015495/meaning-of-in-java-syntax
		todoRepository.saveAll(todos);
	}
}
