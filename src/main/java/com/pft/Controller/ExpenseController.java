package com.pft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pft.Entity.Expense;
import com.pft.Entity.User;
import com.pft.Service.ExpenseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService service;
	
	
	@PostMapping("/expense")
	public String addExpense(@ModelAttribute Expense ex, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		ex.setUser(user);
		service.addExpense(ex);
		return "redirect:/expense";
	}
	
	@GetMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/income";
	}
}
