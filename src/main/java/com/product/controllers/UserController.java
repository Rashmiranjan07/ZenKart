
package com.product.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.product.dto.AddUserDto;
import com.product.dto.ApiResponse;
import com.product.dto.EmailOtpVerifyDto;
import com.product.service.UserService;

@RestController
@RequestMapping("/api/v2/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> initiateUserVerificationController(@RequestBody AddUserDto dto) {
		String serviceResponse = userService.initiateUserVerificationService(dto);
		ApiResponse apiResponse = ApiResponse.builder().serviceName("PRODUCT_SERVICE").status(true).type("string")
				.payload(serviceResponse).build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@PostMapping("/verification")
	public ResponseEntity<ApiResponse> finalUserVerificationController(@RequestBody EmailOtpVerifyDto dto) {
		String serviceResponse = userService.finalUserVerificationService(dto);
		ApiResponse apiResponse = ApiResponse.builder().serviceName("PRODUCT_SERVICE").status(true).type("string")
				.payload(serviceResponse).build();

		return ResponseEntity.ok(apiResponse);
	}

}
