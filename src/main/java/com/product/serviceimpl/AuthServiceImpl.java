package com.product.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.product.service.AuthService;
import com.product.util.JWTUtil;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTUtil jwtutil;

	@Override
	public String authUserNamePasswordService(String username, String password) {
		UsernamePasswordAuthenticationToken token=
				new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication=authManager.authenticate(token);
		if(authentication.isAuthenticated()) {
			List<String> roles=authentication.getAuthorities()
					.stream()
					.map(authrity->authrity.getAuthority()).toList();
			String jwt=jwtutil.createJwtToken(username, roles);
			return jwt;
			
		}
		throw new RuntimeException("Invalid password");
	}

}
