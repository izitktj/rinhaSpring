package com.rinhaback.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
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
		Optional<User> user = service.findByID(id);
		
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(user.get());	
	}

	@GetMapping("/pessoas")
	public ResponseEntity<List<User>> queryUser(@RequestParam("t") String term) {
		if(term.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok(service.findByTerm(term));
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
