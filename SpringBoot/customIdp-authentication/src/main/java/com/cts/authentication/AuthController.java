package com.cts.authentication;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.jwt.JwtResponse;
import com.cts.request.LoginRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	private String jwtSecret = "myjwt";

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		Instant now = Instant.now();
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = Jwts.builder().setSubject(authentication.getName())
				.claim("authorities",
						authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(Date.from(now)).setExpiration(Date.from(now.plusSeconds(24 * 60 * 60)))
				.signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes()).compact();

		List<String> roles = authentication.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(token, null, authentication.getName(), null, roles));
	}

}
