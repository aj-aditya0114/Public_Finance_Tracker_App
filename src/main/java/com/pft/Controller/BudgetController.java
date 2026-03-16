package com.pft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pft.Entity.Budget;
import com.pft.Entity.User;
import com.pft.Service.BudgetService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BudgetController {

	@Autowired
	private BudgetService service;
	
	@PostMapping("/budget")
	public String addBudget(@ModelAttribute Budget budget, HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		
		budget.setUser(user);
		service.addBudget(budget);
		return "redirect:/budget";
	}
	
	@GetMapping("/deleteBudget/{id}")
	public String deleteBudget(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/budget";
	}
}
