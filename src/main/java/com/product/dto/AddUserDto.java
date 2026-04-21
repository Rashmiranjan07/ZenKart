
package com.product.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto {
	@NotBlank(message = "username can't be empty")
	private String name;
	@NotBlank(message = "Please enter valid email")
	private String email;
	@NotBlank(message = "Password can't be empty")
	private String password;
	@NotBlank(message = "Role can't be empty")
	private String role;
}

