package com.pft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pft.Entity.Budget;
import com.pft.Entity.User;
import com.pft.Service.BudgetService;


import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {

	@Autowired
	private BudgetService bService;
	

    @GetMapping("/signup")
    public String signup(Model model) {
    	model.addAttribute("user", new User());
        return "signUp";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    
    @GetMapping("/budget")
    public String openBudget(Model model, HttpSession session) {
    	User user = (User)session.getAttribute("user");
    	
    	model.addAttribute("budget", new Budget());
    	model.addAttribute("budgetlist", bService.getAllBudget(user));
        return "budget";
    }
	    
	   
	
}
