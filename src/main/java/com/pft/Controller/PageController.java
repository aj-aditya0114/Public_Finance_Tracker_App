package com.pft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pft.Entity.Budget;
import com.pft.Entity.Expense;
import com.pft.Entity.Income;
import com.pft.Entity.User;
import com.pft.Service.BudgetService;
import com.pft.Service.ExpenseService;
import com.pft.Service.IncomeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PageController {
	@Autowired
	private IncomeService iService;
	
	@Autowired
	private ExpenseService eService;
	
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
	    
	    @GetMapping("/dashboard")
	    public String openDashboard(Model model, HttpSession session) {
	    	String username = (String)session.getAttribute("name");
	    	model.addAttribute("name", username);
	    	return "dashboard";
	    }
	    
	    @GetMapping("/income")
	    public String openIncome(Model model, HttpSession session) {
	    	User user = (User)session.getAttribute("user"); 
	    	
	    	model.addAttribute("income", new Income());
	    	List<Income> incomes = iService.getAllIncome(user);
	    	
	    	model.addAttribute("incomelist", incomes);
	    
	    	
	        return "income";
	    }
	    
	    @GetMapping("/expense")
	    public String openExpense(Model model, HttpSession session) {
	    	User user = (User)session.getAttribute("user");
	    	
	    	model.addAttribute("expense", new Expense());
	    	model.addAttribute("expenselist", eService.getAllExpense(user));
	        return "expense";
	    }
	    
	    @GetMapping("/budget")
	    public String openBudget(Model model, HttpSession session) {
	    	User user = (User)session.getAttribute("user");
	    	
	    	model.addAttribute("budget", new Budget());
	    	model.addAttribute("budgetlist", bService.getAllBudget(user));
	        return "budget";
	    }
	    
	   
	
}
