package com.pft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pft.Entity.User;
import com.pft.Service.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("user") User user, Model model) {
		userService.registerUser(user);
		//model.addAttribute("user", new User());
		return "redirect:/login";
	}
	
	@PostMapping("/loggingin")
	public String loginUser(@RequestParam String email,
							@RequestParam String password,
							HttpSession session,
							Model model) {
		try {
		User user = userService.loginUser(email, password);
		
		if(user != null) {
			session.setAttribute("user", user);
			session.setAttribute("name", user.getName());
			session.setAttribute("username", user.getName());
			return "redirect:/dashboard";
		}
		model.addAttribute("error", "Invalid email or password");
		
		}catch(Exception e) {
			e.printStackTrace();		
		}
		return "/login";
	}
}
