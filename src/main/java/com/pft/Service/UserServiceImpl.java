package com.pft.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pft.Entity.User;
import com.pft.Repository.UserRepo;

@Service
public class UserServiceImpl{
	
	@Autowired
	private UserRepo userRepo;
	
	//private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


	public String registerUser(User user) {
		User existingUser = userRepo.findByEmail(user.getEmail());
		if(existingUser != null) {
			return "User already registered";
		}
		
		userRepo.save(user);
		return "User registered successfully";
		

	}


	public User loginUser(String input, String password) {
		User user;
		
		if(input.contains("@")) {
			user = userRepo.findByEmail(input);
		}else {
			input = input.replaceAll("[^0-9]", "");
			user = userRepo.findByPhone(input);
		}
		 System.out.println("USER FROM DB = " + user);

	    if (user != null) {
	        System.out.println("DB PASSWORD = " + user.getPassword());
	        System.out.println("ENTERED PASSWORD = " + password);
	    }
		
		
		if(user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}
	
	
	
	

}
