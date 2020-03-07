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
package org.pdbcorp.mgmt.todo.command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author jaradat-pdb
 *
 */
@Data
public class CheckoutCommand {
	@NotEmpty
	@Size(min = 2, max = 50)
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 50)
	private String lastName;

	@NotEmpty
	@Size(min = 2, max = 50)
	private String addressLine1;
	private String addressLine2;

	@NotEmpty
	@Size(min = 2, max = 50)
	private String city;

	@NotEmpty
	@Size(min = 2, max = 2)
	private String stateCode;

	@NotEmpty
	@Size(min = 5, max = 10)
	private String zipCode;
}
