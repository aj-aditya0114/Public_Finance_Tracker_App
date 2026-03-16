package com.pft.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pft.Entity.Income;
import com.pft.Entity.User;
import com.pft.Service.IncomeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService service;
	
	@PostMapping("/income")
	public String addIncome(@ModelAttribute Income income, HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		
		income.setUser(user);
		
		service.saveIncome(income);
		model.addAttribute("success", "Income added Successfully");
		
		return "redirect:/income";
	}
	
	@GetMapping("/deleteIncome/{id}")
	public String deleteIncome(@PathVariable Long id) {
		service.deleteIncome(id);
		return "redirect:/income";
	}
}
