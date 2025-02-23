package com.rinhaback.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rinhaback.api.domain.User.User;
import com.rinhaback.api.domain.User.UserDTO;
import com.rinhaback.api.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping
public class UserController {
	private final UserService service;
	
	@GetMapping
	public List<User> getAllUsers() {
		return service.findAll();
	}

	@GetMapping("/pessoas/{id}")
	public ResponseEntity<User> getUserById(@PathVariable UUID id) {
		try {
			return ResponseEntity.ok(service.findByID(id).get());	
		} 
		catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}

	@GetMapping("/pessoas")
	public ResponseEntity<List<User>> queryUser(@RequestParam("t") String term) {
		try {
			return ResponseEntity.ok(service.findByTerm(term));
		}
		catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/pessoas")
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO user) {
		User userEntity = service.save(user).get();

		return ResponseEntity
		.status(201)
		.header(
			"Location",
			new StringBuilder()
			.append("/pessoas/")
			.append(userEntity.getId())
			.toString()
		)
		.body(user);
	}

	@GetMapping("/contagem-pessoas")
	public long contagemPessoas() {
		return service.countUsers();
	}
}
