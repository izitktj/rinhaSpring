package com.rinhaback.api.domain.User;

import java.util.Date;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	@Nonnull private String apelido;
	@Nonnull private String nome;
	@Nonnull private Date nascimento;
	private String[] stack;
}
