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

import javax.validation.Valid;

import org.pdbcorp.mgmt.todo.command.CheckoutCommand;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author jaradat-pdb
 *
 */
@Controller
public class CheckoutController {
	@GetMapping("/checkout")
	public String checkout() {
		return "checkout";
	}

	@PostMapping("/doCheckout")
	public String doCheckout(@Valid CheckoutCommand checkoutCommand, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "checkout";
		}
		return "checkoutComplete";
	}
}
