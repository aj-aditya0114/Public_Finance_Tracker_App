package com.pft.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.pft.Entity.User;
import com.pft.Repository.UserRepo;

@Service
public class UserServiceImpl{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;


	public String registerUser(User user) {
		User existingUser = userRepo.findByEmail(user.getEmail());
		existingUser.setPassword(encoder.encode(user.getPassword()));
		if(existingUser != null) {
			return "User already registered";
		}
		userRepo.save(user);
		return "User registered successfully";
		

	}

	public User loginUser(String email, String password) {
		User user = userRepo.findByEmail(email);
		
		if(user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

}
