package com.pft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pft.Entity.Budget;
import com.pft.Entity.Expense;
import com.pft.Entity.User;
import com.pft.Service.BudgetService;
import com.pft.Service.ExpenseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService service;
	
	@GetMapping("/expense")
    public String openExpense(Model model, HttpSession session) {
    	User user = (User)session.getAttribute("user");
    	
    	model.addAttribute("expense", new Expense());
    	model.addAttribute("expenselist", service.getAllExpense(user));
    	
        return "expense";
    }
	
	@PostMapping("/expense")
	public String addExpense(@ModelAttribute Expense expense, RedirectAttributes re, HttpSession session) {
		User user = (User) session.getAttribute("user");
		System.out.println("User before set = " + expense.getUser());

		expense.setUser(user);

		System.out.println("User after set = " + expense.getUser());

		String message = service.addExpense(expense);	
		
		if(message.contains("exceeded")) {
			re.addFlashAttribute("error", message);
		}else {
			re.addFlashAttribute("success", message);
		}
		return "redirect:/expense";
	}
	
	@GetMapping("/deleteExpense/{id}")
	public String deleteExpense(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/expense";
	}
	

}
