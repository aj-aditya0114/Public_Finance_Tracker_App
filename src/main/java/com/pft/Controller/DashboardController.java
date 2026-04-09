package com.pft.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pft.Entity.Expense;
import com.pft.Entity.Income;
import com.pft.Entity.User;
import com.pft.Service.ExpenseService;
import com.pft.Service.IncomeService;
import com.pft.Service.TransactionService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
	
	@Autowired
	private IncomeService iService;
	
	@Autowired
	private ExpenseService eService;
	
	@Autowired
	private TransactionService tService;

	@GetMapping("/dashboard")
    public String openDashboard(Model model, HttpSession session) {
    	String name = (String)session.getAttribute("name");
    	model.addAttribute("name", name);
    	User user = (User)session.getAttribute("user");
    	
    	List<Income> incomeList = iService.getAllIncome(user);
    	List<Expense> expenseList = eService.getAllExpense(user);
    	
    	double totalIncome = 0;
    	for(int i = 0; i < incomeList.size(); i++) {
    		totalIncome += incomeList.get(i).getAmount();
    	}
    	
    	double totalExpense = 0;
    	for(int i = 0; i < expenseList.size(); i++) {
    		totalExpense += expenseList.get(i).getAmount();
    	}
    	
    	double savings = totalIncome - totalExpense;
    	
    	model.addAttribute("totalIncome", totalIncome);
    	model.addAttribute("totalExpense", totalExpense);
    	model.addAttribute("savings", savings);
    	
    	model.addAttribute("transaction", tService.getAll());
		model.addAttribute("alert", tService.isOverSpending());
		
		
    	return "dashboard";
    }
}
