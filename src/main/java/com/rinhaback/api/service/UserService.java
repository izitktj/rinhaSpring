package com.rinhaback.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.rinhaback.api.domain.User.User;
import com.rinhaback.api.domain.User.UserDTO;
import com.rinhaback.api.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private final UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public Optional<User> findByID(UUID id) {
		return repository.findById(id);
	}
	
	public User save(UserDTO user) {
		User userEntity = new User();

		userEntity.setApelido(user.getApelido());
		userEntity.setNome(user.getNome());
		userEntity.setNascimento(user.getNascimento());
		userEntity.setStack(user.getStack());

		return repository.save(userEntity);
	}
	
	public void deleteAll() {
		repository.deleteAll();
	}

	public Optional<User> findByApelido(String apelido) {
		return repository.findOneByApelido(apelido);
	}

	public long countUsers() {
		return repository.count();
	}

	public List<User> findByTerm(String term) {
		return repository.findByQueryApelidoNomeStack(term);
	}
}