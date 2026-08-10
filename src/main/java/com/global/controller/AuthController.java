package com.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.global.Service.AuthService;
import com.global.Service.CustomUserDetailService;
import com.global.Service.JWTService;
import com.global.Service.RefreshTokenService;
import com.global.entity.AppUser;
import com.global.entity.RefreshToken;
import com.global.exception.ExpiryRefreshTokenException;

@RequestMapping("/auth")
@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JWTService jwt;
	@Autowired
	AuthService authService;
	@Autowired
	RefreshTokenService refreshTokenService;
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	
	
	@PostMapping("/login")
	public String login(@RequestBody AppUser user) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		UserDetails entity = (UserDetails) authentication.getPrincipal();
		String token = jwt.GenerateToken(entity);
		RefreshToken refreshToken = refreshTokenService.refreshToken(user);
		return " your token is => " + token + " and refresh token is => " + refreshToken.getToken();
	}
	
	
	
	@PostMapping("/insert")
	@PreAuthorize("hasRole('ADIMN')")
	public void insertUser(@RequestBody AppUser user) {
		 authService.insert(user);
	}
	
	
	@PostMapping("/refresh/{token}")
	public String refreshToken(@PathVariable String token) throws ExpiryRefreshTokenException {
		RefreshToken refresh = refreshTokenService.findByToken(token);
		refreshTokenService.verifyRefreshToken(token);
		UserDetails userDetails = customUserDetailService.loadUserByUsername(refresh.getUser().getUsername());
		String accessToken = jwt.GenerateToken(userDetails);
		return accessToken;
	}
	
	
	
	
	
	

}
