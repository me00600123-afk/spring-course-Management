package com.global.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.global.Repo.RefreshTokenRepo;
import com.global.Repo.UserRepo;
import com.global.Service.AuthService;
import com.global.customenum.Role;
import com.global.entity.AppUser;

@Component
public class SartUp implements CommandLineRunner {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	AuthService authService;
	@Autowired
	RefreshTokenRepo refreshTokenRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		if(userRepo.count()==0) {
			AppUser user1 = new AppUser();
			user1.setUsername("me00600123");
			user1.setPassword("123456");
			user1.setRole(Role.ADMIN);
			authService.insert(user1);
			
			AppUser user2 = new AppUser();
			user2.setUsername("ahmed1122");
			user2.setPassword("ahmed1512");
			user2.setRole(Role.STUDENT);
			authService.insert(user2);
		}
	}

}
