package com.global.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.Repo.RefreshTokenRepo;
import com.global.Repo.UserRepo;
import com.global.entity.AppUser;
import com.global.entity.RefreshToken;
import com.global.exception.ExpiryRefreshTokenException;
import com.global.exception.NotFoundException;

@Service
public class RefreshTokenService {
	@Autowired
	RefreshTokenRepo refreshTokenRepo;
	@Autowired
	UserRepo userRepo;
	
	
	
	public RefreshToken refreshToken(AppUser user) {
		AppUser entity = userRepo.findByUsername(user.getUsername()).get();
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setExpiryDate(Instant.now().plusSeconds(7*24*60*60));
		refreshToken.setUser(entity);
		return refreshTokenRepo.save(refreshToken);
	}
	
	
	
	public RefreshToken getToken(String token) {
		return refreshTokenRepo.findByToken(token).get();
	}
	
	public void verifyRefreshToken(String token) throws ExpiryRefreshTokenException {
		if(getToken(token).getExpiryDate().isBefore(Instant.now())) {
			throw new ExpiryRefreshTokenException("refresh token useless");
		}
	}
	
	
	public RefreshToken findByToken(String token) {
		Optional<RefreshToken> entity = refreshTokenRepo.findByToken(token);
		if(!entity.isPresent()) {
			throw new NotFoundException("invalid refresh token");
		}
		return refreshTokenRepo.findByToken(token).get();
	}
	
	
	

}
