package org.pruebatecnica.zoo.mappers;


import org.pruebatecnica.zoo.dtos.AuthorizationRequest;
import org.pruebatecnica.zoo.dtos.UserResponse;
import org.pruebatecnica.zoo.entities.Usuario;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(Usuario user) {
		return UserResponse.builder().name(user.getEmail()).id(Integer.parseInt(user.getDocumento())).build();
	}

	public static Usuario toDomain(AuthorizationRequest authorizationRequest) {
		return Usuario.builder().email(authorizationRequest.getUserName()).password(authorizationRequest.getPassword())
				.build();
	}
}
