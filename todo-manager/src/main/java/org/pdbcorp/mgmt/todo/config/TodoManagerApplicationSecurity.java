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
package org.pdbcorp.mgmt.todo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author jaradat-pdb
 *
 */
@Configuration
public class TodoManagerApplicationSecurity extends WebSecurityConfigurerAdapter {
	private static final String ADMIN_ROLE = "ADMIN";
	private static final String ROOT_ROLE = "ROOT";
	private static final String USER_ROLE = "USER";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/css/**", "/js/**", "/**/favicon.ico").permitAll()
			.antMatchers("/todos", "/checkout", "/doCheckout").hasRole(USER_ROLE)
			.antMatchers("/admin", "/h2/**", "/actuator/health").hasRole(ADMIN_ROLE)
			.antMatchers("/initiateShutdown", "/actuator/**").access("hasRole('ROOT')")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout().permitAll();
		http.exceptionHandling().accessDeniedPage("/error");
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("{noop}password").roles(USER_ROLE)
			.and()
			.withUser("admin").password("{noop}secret").roles(ADMIN_ROLE, USER_ROLE)
			.and()
			.withUser("root").password("{noop}secretRootPasswd").roles(ROOT_ROLE, ADMIN_ROLE, USER_ROLE);
	}
}
