package com.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.dto.ApiResponse;
import com.product.service.AuthService;

@RestController
@RequestMapping("/api/v3/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse> authenticateUserNamePasswordController(@RequestParam String username,@RequestParam String password) {
		String serviceResponse = authService.authUserNamePasswordService(username, password);
		ApiResponse apiResponse = ApiResponse.builder()
				.serviceName("PRODUCT_SERVICE")
				.status(true).type("string")
				.payload(serviceResponse).build();
		return ResponseEntity.ok(apiResponse);

	}

}
