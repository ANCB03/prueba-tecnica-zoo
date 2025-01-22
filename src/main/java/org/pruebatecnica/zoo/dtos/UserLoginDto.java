package org.pruebatecnica.zoo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    private String token;
    private String username;

    private String rol;

}
