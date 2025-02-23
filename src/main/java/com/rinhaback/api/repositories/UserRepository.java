package com.rinhaback.api.repositories;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rinhaback.api.domain.User.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	@Query(value = "SELECT * FROM users WHERE apelido LIKE %:query% OR nome LIKE %:query% OR EXISTS (SELECT 1 FROM UNNEST(stack) AS s WHERE s LIKE %:query%)", nativeQuery = true)
	List<User> findByQueryApelidoNomeStack(@Param("query") String query);

	Optional<User> findOneByApelido(String apelido);
}
